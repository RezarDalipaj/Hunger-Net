package backend.service;

import backend.dto.RestaurantDto;
import backend.model.Restaurant;
import backend.model.User;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = {"backend"})
public interface RestaurantService {
    RestaurantDto save(RestaurantDto restaurantDto);
    List<RestaurantDto> findAll();
    RestaurantDto findRestaurantByName(String name);
    RestaurantDto findRestaurantByManager(User user);
}
