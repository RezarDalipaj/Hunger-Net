package backend.service;

import backend.dto.BookingDto;
import backend.dto.FlightDto;
import backend.dto.UserDto;
import backend.model.Flight;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan(basePackages = {"backend"})
public interface FlightService {
    FlightDto save(Flight f);
    FlightDto findById(Integer id);
    public Flight findByIdd(Integer id);
    List<FlightDto> findAll();
    void delete(Flight f);
    void deleteById(Integer id);
    List<FlightDto> deleteAll();
    FlightDto convertFlightToDto(Flight f);
    Flight convertDtoToFlightAdd(FlightDto flightDTO);
    Flight convertDtoToFlightUpdate(FlightDto flightDTO, Integer id);
    Flight findFlightByFlightNumber(Integer flightNumber);
    List<BookingDto> findAllBookings(Integer id);
    List<UserDto> findAllUsers(Integer id);

}
