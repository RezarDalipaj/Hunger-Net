package web.controller;

import backend.configuration.security.config.JwtTokenUtil;
import backend.dto.*;
import backend.model.dto.AdminDto;
import backend.model.dto.LogOutDto;
import backend.model.dto.UserDto;
import backend.service.AdminService;
import backend.service.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtAuthenticationController authController;
    private final AdminService adminService;
    private final JwtTokenUtil jwtTokenUtil;

    UserController(UserService userService, JwtAuthenticationController authController
            , AdminService adminService, JwtTokenUtil jwtTokenUtil){
        this.userService = userService;
        this.authController = authController;
        this.adminService = adminService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getWithoutAdmin(HttpServletRequest request){
        String username = authController.usernameFromToken(request);
        UserDto userDto = userService.findUserByUserName(username);
        List<UserDto> userDtoList = userService.findAllWithoutAdmin();
        //adding current admin to the users list
        userDtoList.add(0, userDto);
        return ResponseEntity.ok(userDtoList);
    }
    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getUsersByRole(@RequestParam(name = "role") String role) throws Exception{
        return ResponseEntity.ok(userService.findAllByRole(role));
    }
    @PostMapping( "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveUser(@RequestBody AdminDto adminDto) throws Exception {
        adminDto.setId(null);
        return ResponseEntity.ok(adminService.save(adminDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id, HttpServletRequest request) throws Exception{
        UserDto userDTO = userService.findById(id);
        //only admin and the actual user can see the user info
        if (!(authController.usernameFromToken(request)).equals(userDTO.getUserName())
                && !authController.isAdmin(request))
            throw new AuthenticationException();
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/{id}/logout")
    public ResponseEntity<?> logout(@PathVariable(name = "id") Integer id, HttpServletRequest request) throws Exception {
        UserDto userDTO = userService.findById(id);
        if (!(authController.usernameFromToken(request)).equals(userDTO.getUserName()))
            throw new AuthenticationException();
        jwtTokenUtil.setLoggedOut(true);
        LogOutDto logOutDto = new LogOutDto();
        logOutDto.setMessage(userDTO.getUserName() + " is logged out");
        return ResponseEntity.ok(logOutDto);
    }
    @GetMapping("/username")
    public ResponseEntity<?> getUsersByUsername(@RequestParam(name = "username") String username
            , HttpServletRequest request) throws Exception{
        UserDto userDTO = userService.findUserByUserName(username);
        if (userDTO == null)
            throw new NullPointerException("USER WITH USERNAME " + username);
        //admin and user with that username can access this API
        if (!(authController.usernameFromToken(request)).equals(username)
                && !authController.isAdmin(request))
            throw new AuthenticationException();
        return ResponseEntity.ok(userDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id, HttpServletRequest request) throws Exception{
        UserDto userDTO = userService.findById(id);
        if (!(authController.usernameFromToken(request)).equals(userDTO.getUserName())
                && !authController.isAdmin(request))
            throw new AuthenticationException();
        userService.deleteById(id);
        return ResponseEntity.ok(userDTO);
    }
}
