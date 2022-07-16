package web.controller;

import backend.dto.RestaurantDto;
import backend.service.RestaurantService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ComponentScan(basePackages = {"backend"})
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> get(){
        try {
            return ResponseEntity.ok(restaurantService.findAll());
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(401), "Users not found");
        }
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> post(@RequestBody RestaurantDto restaurantDto){
        try {
            return ResponseEntity.ok(restaurantService.save(restaurantDto));
        }catch (IllegalArgumentException e){
            throw  new ResponseStatusException(HttpStatus.resolve(400), "Users not found");
        }
    }
}
