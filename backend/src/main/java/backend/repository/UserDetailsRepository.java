package backend.repository;

import backend.model.User;
import backend.model.UserDetails;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan(basePackages = {"backend"})
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findFirstByTheUser(User user);
    @Query(value = "SELECT * FROM user_details WHERE user_id = :id", nativeQuery = true)
    UserDetails findUserDetailsByUserId(Integer id);
    List<UserDetails> findAllByFirstNameContainsIgnoreCase(String fname);
    List<UserDetails> findAllByEmailContainsIgnoreCase(String email);
    List<UserDetails> findAllByPhoneNumberContainsIgnoreCase(String phone);
}
