package backend.service;

import backend.model.User;
import backend.model.UserDetails;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
@ComponentScan(basePackages = {"backend"})
public interface UserDetailsService {
    UserDetails save(UserDetails u);
    Optional<UserDetails> findById(Integer id);
    UserDetails findByUser(User user);
    List<UserDetails> findFirstByFirstName(String fname);
    List<UserDetails> findFirstByEmail(String email);
    List<UserDetails> findFirstByPhoneNumber(String phone);
}
