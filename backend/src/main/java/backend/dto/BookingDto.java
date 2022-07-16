package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
@Component
public class BookingDto {
    private String userName;
    private String status;
    private Date bookingDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FlightDto> flightList;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> flightNumbers;

    public List<Integer> getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List<Integer> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<FlightDto> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<FlightDto> flightList) {
        this.flightList = flightList;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", bookingDate=" + bookingDate +
                ", flightList=" + flightList +
                '}';
    }
}
