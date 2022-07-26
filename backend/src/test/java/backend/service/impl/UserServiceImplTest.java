package backend.service.impl;

import backend.dto.UserDto;
import backend.model.Status;
import backend.model.User;
import backend.model.UserDetails;
import backend.repository.*;
import backend.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;


class UserServiceImplTest {
    private UserRepository userRepository;
    private UserDetailsRepository userDetailsRepository;
    private RoleRepository roleRepository;
    private RestaurantServiceImpl restaurantService;
    private StatusRepository statusRepository;
    private OrderStatusRepository orderStatusRepository;
    private MenuTypeRepository menuTypeRepository;
    private PasswordEncoder bcryptEncoder;
    private OrderService orderService;
    private UserServiceImpl userService;
    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsRepository = Mockito.mock(UserDetailsRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        restaurantService = Mockito.mock(RestaurantServiceImpl.class);
        statusRepository = Mockito.mock(StatusRepository.class);
        orderStatusRepository = Mockito.mock(OrderStatusRepository.class);
        menuTypeRepository = Mockito.mock(MenuTypeRepository.class);
        bcryptEncoder = Mockito.mock(PasswordEncoder.class);
        orderService = Mockito.mock(OrderService.class);
        userService = new UserServiceImpl(userRepository, userDetailsRepository
                , roleRepository, statusRepository, orderStatusRepository
                , restaurantService, menuTypeRepository, bcryptEncoder, orderService);
    }
    @Test
    void user_savee_returns_user_dto_when_saved_successfully() throws Exception{
        UserDto userDto = new UserDto();
        userDto.setUserName("new_user");
        userDto.setPassword("new_user");
        userDto.setId(1);
        userDto.setFirstName("new_user");
        userDto.setLastName("new_user");
        userDto.setEmail("new_user");
        userDto.setPhoneNumber("451551");
        userDto.setBalance(1000.0);
        Status status = new Status();
        status.setStatus("VALID");

        User persistentUser = new User();
        persistentUser.setId(1);
        persistentUser.setUserName("old_user");
        persistentUser.setPassword("new_password");
        persistentUser.setStatus(status);
        persistentUser.setUserDetails(new UserDetails());


        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(persistentUser));
        Mockito.when(userRepository.save(any())).thenReturn(persistentUser);
        Mockito.when(userDetailsRepository.save(any())).thenReturn(Optional.of(persistentUser.getUserDetails()));
        Mockito.when(bcryptEncoder.encode(any())).thenReturn("test");
        Mockito.when(statusRepository.findStatusByStatus(any())).thenReturn(status);

        UserDto newUser = userService.save(userDto);
        assertEquals(userDto.getUserName(), newUser.getUserName());
    }
    @Test
    void user_update_returns_user_dto_when_updated_successfully() throws Exception{
        UserDto userDto = new UserDto();
        userDto.setUserName("new_user");
        userDto.setPassword("new_user");
        userDto.setId(1);
        userDto.setFirstName("new_user");
        userDto.setLastName("new_user");
        userDto.setEmail("new_user");
        userDto.setPhoneNumber("451551");
        userDto.setBalance(1000.0);
        Status status = new Status();
        status.setStatus("VALID");

        User persistentUser = new User();
        persistentUser.setId(1);
        persistentUser.setUserName("old_user");
        persistentUser.setPassword("new_password");
        persistentUser.setStatus(status);
        persistentUser.setUserDetails(new UserDetails());


        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(persistentUser));
        Mockito.when(userRepository.save(any())).thenReturn(persistentUser);
        Mockito.when(userDetailsRepository.save(any())).thenReturn(Optional.of(persistentUser.getUserDetails()));
        Mockito.when(bcryptEncoder.encode(any())).thenReturn("test");
        Mockito.when(statusRepository.findStatusByStatus(any())).thenReturn(status);

        UserDto newUser = userService.save(userDto);
        assertEquals(userDto.getUserName(), newUser.getUserName());
    }
}