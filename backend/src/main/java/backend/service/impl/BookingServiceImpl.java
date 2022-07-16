package backend.service.impl;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import backend.dto.BookingDto;
import backend.dto.FlightDto;
import backend.model.Booking;
import backend.model.Flight;
import backend.model.User;
import backend.repository.BookingRepository;
import backend.repository.UserRepository;
import backend.service.BookingService;
import backend.service.FlightService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private FlightService flightService;
    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
        this.userRepository = userRepository;
    }

    public BookingDto save(Booking b){
        Booking booking = bookingRepository.save(b);
        return convertBookingToDto(booking);
    }
    public BookingDto findById(Integer id){
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            return convertBookingToDto(booking);
        }
        return null;
    }

    @Override
    public Booking findByIdd(Integer id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            return booking;
        }
        return null;
    }

    public List<BookingDto> findAll(){
        List<BookingDto> bookingDTOS = bookingRepository.findAll().stream().map(this::convertBookingToDto).collect(Collectors.toList());
        return bookingDTOS;
    }
    public void delete(Booking u){
            bookingRepository.delete(u);
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingDto> deleteAll() {
        List<BookingDto> bookingDtoList = findAll();
        bookingRepository.deleteAll();
        return bookingDtoList;
    }

    @Override
    public BookingDto convertBookingToDto(Booking b) {
        BookingDto bookingDTO = new BookingDto();
        bookingDTO.setUserName(b.getUser().getUserName());
        bookingDTO.setBookingDate(b.getBookingDate());
        bookingDTO.setStatus(b.getStatus());
        List<FlightDto> flightDTOList = new ArrayList<>();
        List<Flight> flightList = b.getFlights();
        convertFlights(flightDTOList, flightList);
        bookingDTO.setFlightList(flightDTOList);
        return bookingDTO;
    }

    @Override
    public Booking convertDtoToBookingAdd(BookingDto bookingDto) {
        Booking booking = new Booking();
        return setBooking(bookingDto, booking);
    }
    public Booking setBooking(BookingDto bookingDto, Booking booking){
        User user = userRepository.findUserByUserName(bookingDto.getUserName());
        if (user == null)
            return null;
        booking.setUser(user);
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setStatus(bookingDto.getStatus());
        List<Integer> flightNumbers = bookingDto.getFlightNumbers();
        List<Flight> flightList = new ArrayList<>();
        for (Integer i:flightNumbers) {
            if (flightService.findFlightByFlightNumber(i) == null)
                return null;
            Flight flight = flightService.findFlightByFlightNumber(i);
            flightList.add(flight);
        }
        booking.setFlights(flightList);
        return booking;
    }
    @Override
    public Booking convertDtoToBookingUpdate(BookingDto bookingDto, Integer id) {
        Booking booking = findByIdd(id);
        return setBooking(bookingDto, booking);
    }

    void convertFlights (List<FlightDto> flightDTOList, List<Flight> flightList){
        for (Flight f:flightList) {
            FlightDto flightDTO = new FlightDto();
            flightDTO.setAirline(f.getAirline());
            flightDTO.setOrigin(f.getOrigin());
            flightDTO.setDestination(f.getDestination());
            flightDTO.setFlightNumber(flightDTO.getFlightNumber());
            flightDTOList.add(flightDTO);
        }
    }
    @Override
    public List<FlightDto> findAllFlights(Integer id) {
        List<Integer> flightIds = bookingRepository.findAllFlightsOfABooking(id);
        List<FlightDto> flightDtoList = new ArrayList<>();
        for (Integer i:flightIds) {
            FlightDto flightDto = flightService.findById(i);
            flightDtoList.add(flightDto);
        }
        return flightDtoList;
    }
}
