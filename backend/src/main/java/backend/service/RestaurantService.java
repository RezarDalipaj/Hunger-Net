package backend.service;

import backend.model.dto.RestaurantDto;
import backend.model.Restaurant;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = {"backend"})
public interface RestaurantService {
    Restaurant findByManager(String username);

    RestaurantDto findById(Integer id);

    RestaurantDto findByIdForManager(Integer id);

    Restaurant findByIdd(Integer id);

    RestaurantDto save(RestaurantDto restaurantDto) throws Exception;
    List<RestaurantDto> findAll();
    List<RestaurantDto> findAllValid();

    List<RestaurantDto> findAllValidForAdmin();

    Integer nrOfRestaurants();
    RestaurantDto findRestaurantByName(String name);
    Restaurant findByName(String name);
    RestaurantDto findRestaurantByManager(String username);

    RestaurantDto saveFromRepository(Restaurant restaurant);

    void deleteById(Integer id);

    List<RestaurantDto> deleteAll();
}
