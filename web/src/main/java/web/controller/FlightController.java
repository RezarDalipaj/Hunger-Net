package web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import backend.dto.BookingDto;
import backend.dto.FlightDto;
import backend.dto.UserDto;
import backend.model.Flight;
import backend.service.FlightService;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"backend"})
@RequestMapping("/flights")
public class FlightController {
    FlightService flightService;
    FlightController(FlightService flightService){
        this.flightService = flightService;
    }
    @GetMapping
    public List<FlightDto> get(){
        try {
            return flightService.findAll();
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Flights not found");
        }
    }
    @GetMapping("/{id}")
    public FlightDto getById(@PathVariable(name = "id") Integer id){
        try {
            FlightDto flightDTO = flightService.findById(id);
            if (flightDTO != null){
                return flightDTO;
            }
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Flight doesn't exist");
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(404), "User doesn't exist");
        }
    }
    @GetMapping("/{id}/bookings")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BookingDto> getBookingsOfAFlight(@PathVariable(name = "id") Integer id){
        try {
            List<BookingDto> bookingDtoList = flightService.findAllBookings(id);
            return bookingDtoList;
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(404), "User doesn't exist");
        }
    }
    @GetMapping("/{id}/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getUsersOfAFlight(@PathVariable(name = "id") Integer id){
        try {
            List<UserDto> userDtoList = flightService.findAllUsers(id);
            return userDtoList;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "User doesn't exist");
        }
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightDto post(@RequestBody FlightDto flightDto){
        try {
        if (flightDto.getFlightNumber() == null)
            throw  new ResponseStatusException(HttpStatus.resolve(400), "Invalid data");
        Flight flight = flightService.convertDtoToFlightAdd(flightDto);
        return flightService.save(flight);
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(400), "Invalid data");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightDto put(@PathVariable(name = "id") Integer id, @RequestBody FlightDto flightDto){
        try {
            if (flightDto.getFlightNumber() == null)
                throw  new ResponseStatusException(HttpStatus.resolve(400), "Invalid data");
            if (flightService.findByIdd(id) != null){
                Flight flight = flightService.convertDtoToFlightUpdate(flightDto, id);
                if (flight != null)
                    return flightService.save(flight);
                throw  new ResponseStatusException(HttpStatus.resolve(400), "Invalid data");
            }
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Invalid data");
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(400), "Invalid data");
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightDto delete(@PathVariable(name = "id") Integer id){
        try {
            FlightDto flightDTO = flightService.findById(id);
            if (flightDTO != null){
                flightService.deleteById(id);
                return flightDTO;
            }
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Invalid data");
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(404), "User doesn't exist");
        }
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FlightDto> deleteAll(){
        try {
            return flightService.deleteAll();
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Invalid data");
        }
    }
}
