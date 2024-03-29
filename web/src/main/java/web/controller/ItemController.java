package web.controller;

import backend.customException.InvalidDataException;
import backend.model.dto.ItemDto;
import backend.model.Item;
import backend.model.Menu;
import backend.model.Restaurant;
import backend.service.MenuService;
import backend.service.impl.ItemServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@RestController
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
@RequestMapping("/items")
public class ItemController {
    private final JwtAuthenticationController authController;
    private final MenuService menuService;
    private final ItemServiceImpl itemService;

    public ItemController(JwtAuthenticationController authController
            , MenuService menuService, ItemServiceImpl itemService) {
        this.authController = authController;
        this.menuService = menuService;
        this.itemService = itemService;
    }
    @GetMapping
    public ResponseEntity<?> getAllItems(HttpServletRequest request){
        //admin gets all items, other users get the items of active menus
        if (authController.isAdmin(request))
            return ResponseEntity.ok(itemService.findAllValid());
        return ResponseEntity.ok(itemService.findAllForClients());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id, HttpServletRequest request) throws Exception{
        if (authController.isAdmin(request))
            return ResponseEntity.ok(itemService.findById(id));
        Item item = itemService.findByIdd(id);
        if (item == null)
            throw new NullPointerException("ITEM WITH ID " + id);
        if (item.getMenu() == null)
            throw new InvalidDataException("Item does not have a menu");
        Menu menu = item.getMenu();
        String username = authController.usernameFromToken(request);
        if (menu.getRestaurant() == null)
            throw new InvalidDataException("Invalid item");
        Restaurant restaurant = menu.getRestaurant();
        //manager gets the item if it is invalid, but should be from his restaurant
        if (restaurant.getManager() != null && restaurant.getManager().getUserName().equals(username))
            return ResponseEntity.ok(itemService.findById(id));
        if (itemService.findByIdValid(id) == null)
            throw new NullPointerException("ITEM WITH ID " + id);
        return ResponseEntity.ok(itemService.convertItemToDto(itemService.findByIdValid(id)));
    }
    @PutMapping( "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id
            , @RequestBody ItemDto itemDto, HttpServletRequest request) throws Exception{
        itemDto.setId(id);
        String username = authController.usernameFromToken(request);
        Item item = itemService.findByIdd(id);
        if (item == null)
            throw new NullPointerException("ITEM WITH ID " + id);
        if (item.getMenu() == null)
            throw new AuthenticationException();
        Menu menu = item.getMenu();
        if (menu.getRestaurant() == null)
            throw new AuthenticationException();
        Restaurant restaurant = menu.getRestaurant();
        if (restaurant.getManager() != null && (restaurant.getManager().getUserName().equals(username))) {
            //validating if the menu written belongs to the restaurant of the manager
            if (itemDto.getMenuId() == null)
                throw new InvalidDataException("Menu is required");
            Menu menu1 = menuService.findByIdAll(itemDto.getMenuId());
            if (menu1 == null)
                throw new InvalidDataException("Menu does not exist");
            if (!menu.getRestaurant().equals(menu1.getRestaurant()))
                throw new AuthenticationException();
            return ResponseEntity.ok(itemService.save(itemDto));
        }
        throw new AuthenticationException();
    }
    @PostMapping
    public ResponseEntity<?> saveItem(@RequestBody ItemDto itemDto, HttpServletRequest request) throws Exception{
        itemDto.setId(null);
        String username = authController.usernameFromToken(request);
        if (itemDto.getMenuId() == null)
            throw new InvalidDataException("Menu is required");
        Menu menu = menuService.findByIdAll(itemDto.getMenuId());
        if (menu == null)
            throw new InvalidDataException("Menu does not exist");
        if (menu.getRestaurant() == null)
            throw new AuthenticationException();
        if (menu.getRestaurant().getManager() == null)
            throw new AuthenticationException();
        String user = menu.getRestaurant().getManager().getUserName();
        if (user.equals(username))
            return ResponseEntity.ok(itemService.save(itemDto));
        throw new AuthenticationException();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id, HttpServletRequest request) throws Exception{
        ItemDto itemDto = itemService.findById(id);
        String username = authController.usernameFromToken(request);
        Item item = itemService.findByIdd(id);
        if (item.getMenu() == null)
            throw new AuthenticationException();
        Menu menu = item.getMenu();
        if (menu.getRestaurant() == null)
            throw new AuthenticationException();
        Restaurant restaurant = menu.getRestaurant();
        if (restaurant.getManager() != null && (restaurant.getManager().getUserName().equals(username))) {
            itemService.deleteById(id);
            return ResponseEntity.ok(itemDto);
        }
        throw new AuthenticationException();
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteAll() {
        return ResponseEntity.ok(itemService.deleteAll());
    }
}
