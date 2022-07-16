package backend.configuration.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import backend.dto.UserDto;
import backend.model.Role;
import backend.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
@ComponentScan(basePackages = {"backend"})
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		backend.model.User user = userService.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		if (user.getRoles() != null){
			Collection<SimpleGrantedAuthority> authorityCollection = new ArrayList<>();
			for (Role role: user.getRoles()) {
				authorityCollection.add(new SimpleGrantedAuthority(role.getRole()));
			}
			return new User(user.getUserName(), user.getPassword(), authorityCollection);
		}
		else {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(user.getUserName(), user.getPassword(), authorities);
		}
	}
	
	public UserDto save(UserDto user) {
		backend.model.User newUser = userService.convertDtoToUserAdd(user);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userService.save(newUser);
	}
	public UserDto put(UserDto user, Integer id) {
		backend.model.User newUser = userService.convertDtoToUserUpdate(user, id);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userService.save(newUser);
	}
}