package backend.service.impl;

import backend.dto.RestaurantDto;
import backend.model.Restaurant;
import backend.model.Role;
import backend.model.User;
import backend.repository.RestaurantRepository;
import backend.repository.RoleRepository;
import backend.service.RestaurantService;
import backend.service.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserService userService, RoleRepository roleRepository){
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }
    @Override
    public List<RestaurantDto> findAll(){
        List<RestaurantDto> restaurantDtoList = restaurantRepository.findAll().stream().map(this::convertRestaurantToDto).collect(Collectors.toList());
        return restaurantDtoList;
    }

    @Override
    public RestaurantDto findRestaurantByName(String name) {
        Restaurant restaurant = restaurantRepository.findRestaurantByName(name);
        if (restaurant != null)
            return convertRestaurantToDto(restaurantRepository.findRestaurantByName(name));
        else
            return null;
    }

    @Override
    public RestaurantDto findRestaurantByManager(User user) {
        return null;
    }

    @Override
    public RestaurantDto save(RestaurantDto restaurantDto){
        try {
            Restaurant restaurant = convertDtoToRestaurantAdd(restaurantDto);
            restaurantRepository.save(restaurant);
            return convertRestaurantToDto(restaurant);
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
    private RestaurantDto convertRestaurantToDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setManager(restaurant.getManager().getUserName());
        restaurantDto.setName(restaurant.getName());
        return restaurantDto;
    }
    private Restaurant convertDtoToRestaurantAdd(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        User user = userService.findByUserName(restaurantDto.getManager());
        Role role = roleRepository.findRoleByRole("RESTAURANT_MANAGER");
        if (restaurantDto.getName() == null || restaurantDto.getManager() == null)
            throw new TypeNotPresentException("Insert data",new Exception());
        if (!(user.getRoles().contains(role)) || findRestaurantByName(restaurantDto.getName()) != null) {
            throw new IllegalArgumentException("Manager already has a restaurant");
        }
        restaurant.setManager(userService.findByUserName(restaurantDto.getManager()));
        restaurant.setName(restaurantDto.getName());
        return restaurant;
    }
}
