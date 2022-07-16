package backend.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import backend.model.Booking;

import java.util.List;

@Repository
@ComponentScan(basePackages = {"backend"})
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    void deleteBookingById(Integer id);
    @Override
    void deleteAll();
    @Query(value = "SELECT id FROM flight where id IN(SELECT flight_id FROM new_db.booking_flight WHERE booking_id = :id)", nativeQuery = true)
    List<Integer> findAllFlightsOfABooking(@Param("id") Integer id);
}
