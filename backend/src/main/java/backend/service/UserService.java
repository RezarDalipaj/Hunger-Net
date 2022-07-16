package backend.service;

import backend.dto.*;
import backend.model.Booking;
import backend.model.User;
import backend.model.UserDetails;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan(basePackages = {"backend"})
public interface UserService {
    UserDto save(User user);
    UserDto findById(Integer id);
    User findByIdd(Integer id);
    List<UserDto> findAll();
    UserDto delete(User u);
    void deleteById(Integer id);
    List<UserDto> deleteAll();
    UserDto convertUserToDto(User u);
    User convertDtoToUserAdd(UserDto userDto);
    User convertDtoToUserUpdate(UserDto userDto, Integer id);
    User setRoles(User user, RoleDto roleDto);
    UserDto findUserByBookings(Booking b);
    UserDto findUserByUserName(String username);
    User findByUserName(String username);
    UserDto findAllByUserDetails(UserDetails ud);
    List<BookingDto> findAllBookings(Integer id);
    List<FlightDto> findAllFlights(int id);


}
