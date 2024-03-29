package backend.service;

import backend.model.dto.RoleDto;
import backend.model.dto.UserDto;
import backend.model.User;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan(basePackages = {"backend"})
public interface UserService {
    UserDto save(UserDto userDto) throws Exception;
    UserDto saveFromRepository(User user);
    UserDto findById(Integer id);
    User findByIdd(Integer id);

    List<UserDto> findAllWithoutAdmin();

    List<UserDto> findAllByRole(String role) throws Exception;

    List<UserDto> findAll();
    List<UserDto> findAllValid();
    Integer nrOfUsers();
    void deleteById(Integer id);
    User setRoles(User user, RoleDto roleDto) throws Exception;
    UserDto findUserByUserName(String username);
    User findByUserName(String username);

}
