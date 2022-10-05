package backend.util;

import backend.model.dto.UserDto;
import backend.model.Role;
import backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "user.userDetails.firstName", target = "firstName")
    @Mapping(source = "user.userDetails.lastName", target = "lastName")
    @Mapping(source = "user.userDetails.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.userDetails.email", target = "email")
    UserDto convertUserToDto(User user);
    default String rolesToString(Role role) {
        return role == null ? null : role.getRole();
    }
}
