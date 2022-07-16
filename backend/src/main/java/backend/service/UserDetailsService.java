package backend.service;

import backend.model.User;
import backend.model.UserDetails;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
@ComponentScan(basePackages = {"backend"})
public interface UserDetailsService {
    public UserDetails save(UserDetails u);
    public Optional<UserDetails> findById(Integer id);
    UserDetails findByUser(User user);
    public List<UserDetails> findFirstByFirstName(String fname);
    public List<UserDetails> findFirstByEmail(String email);
    public List<UserDetails> findFirstByPhoneNumber(String phone);

//    public List<UserDetails> findAll();
//    public void delete(UserDetails u);
}
