package backend.service.impl;

import backend.model.User;
import backend.model.UserDetails;
import backend.repository.UserDetailsRepository;
import backend.service.UserDetailsService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDetailsRepository userDetailsRepository;
    UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }
    public UserDetails save(UserDetails u){
        return userDetailsRepository.save(u);
    }

    @Override
    public Optional<UserDetails> findById(Integer id) {
        return userDetailsRepository.findById(id);
    }

    @Override
    public UserDetails findByUser(User user) {
        return userDetailsRepository.findFirstByTheUser(user);
    }

    public List<UserDetails> findFirstByFirstName(String fname){
        return userDetailsRepository.findAllByFirstNameContainsIgnoreCase(fname);
    }

    @Override
    public List<UserDetails> findFirstByEmail(String email) {
        return userDetailsRepository.findAllByEmailContainsIgnoreCase(email);
    }

    public List<UserDetails> findFirstByPhoneNumber(String phone){
        return userDetailsRepository.findAllByPhoneNumberContainsIgnoreCase(phone);
    }

}
