package backend.service.impl;

import backend.dto.RestaurantDto;
import backend.model.Status;
import backend.repository.RestaurantRepository;
import backend.repository.RoleRepository;
import backend.repository.StatusRepository;
import backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceImplTest {
    private RestaurantRepository restaurantRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private StatusRepository statusRepository;
    private MenuServiceImpl menuService;
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    void setUp(){
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        userService = Mockito.mock(UserService.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        statusRepository = Mockito.mock(StatusRepository.class);
        menuService = Mockito.mock(MenuServiceImpl.class);
        restaurantService = new RestaurantServiceImpl(restaurantRepository
                ,userService,roleRepository,statusRepository,menuService);
    }
    @Test
    void returns_restaurant_dto_when_saved_successfully() throws Exception{
        RestaurantDto restaurantToBeSaved = new RestaurantDto();
        restaurantToBeSaved.setName("restaurant");
        restaurantToBeSaved.setId(null);

        Status status = new Status();
        status.setStatus("VALID");

        Mockito.when(restaurantRepository.findRestaurantByName(any())).thenReturn(null);
        Mockito.when(statusRepository.findStatusByStatus(any())).thenReturn(status);

        RestaurantDto savedRestaurant = restaurantService.save(restaurantToBeSaved);
        assertEquals(savedRestaurant.getName(), restaurantToBeSaved.getName());
    }
}