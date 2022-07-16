package backend.service;

import backend.dto.BookingDto;
import backend.dto.FlightDto;
import backend.model.Booking;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan(basePackages = {"backend"})
public interface BookingService {
    BookingDto save(Booking booking);
    BookingDto findById(Integer id);
    Booking findByIdd(Integer id);
    List<BookingDto> findAll();
    void delete(Booking booking);
    void deleteById(Integer id);
    List<BookingDto> deleteAll();
    BookingDto convertBookingToDto(Booking b);
    Booking convertDtoToBookingAdd(BookingDto bookingDto);
    Booking convertDtoToBookingUpdate(BookingDto bookingDto, Integer id);
    List<FlightDto> findAllFlights(Integer id);

}
