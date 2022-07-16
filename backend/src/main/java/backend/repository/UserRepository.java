package backend.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import backend.model.Booking;
import backend.model.User;
import backend.model.UserDetails;

import java.util.List;

@Repository
@ComponentScan(basePackages = {"backend"})
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByBookings(Booking b);
    User findUserByUserName(String username);
    User findAllByUserDetails(UserDetails ud);
    void deleteUserById(Integer id);
    @Override
    void deleteAll();
    @Query(value = "SELECT id FROM booking WHERE user_id = :id", nativeQuery = true)
    List<Integer> findAllBookingsOfAUser(@Param("id") Integer id);
    @Query(value = "SELECT flight_id FROM booking_flight WHERE booking_id IN(SELECT id FROM booking WHERE user_id =:bookingId)", nativeQuery = true)
    List<Integer> findAllFlightsOfAUser(@Param("bookingId") int id);
    @Query(value = "SELECT role_id FROM user_roles WHERE user_id = :id", nativeQuery = true)
    List<Integer> findRolesOfAUser(@Param("id") Integer id);
}
