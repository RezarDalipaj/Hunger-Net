package backend.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import backend.model.Flight;

import java.util.List;

@Repository
@ComponentScan(basePackages = {"backend"})
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    void deleteFlightById(Integer id);
    @Override
    void deleteAll();
    Flight findFlightByFlightNumber(Integer flightNumber);
    @Query(value = "SELECT id FROM booking WHERE id IN(SELECT booking_id FROM new_db.booking_flight WHERE flight_id = :id)", nativeQuery = true)
    List<Integer> findAllBookingsOfAFlight(@Param("id") Integer id);
    @Query(value = "SELECT id from user where id IN(select user_id from booking where id IN(select booking_id from booking_flight where flight_id = :flightId))", nativeQuery = true)
    public List<Integer> findAllUsersOfAFlight(@Param("flightId") int id);
}
