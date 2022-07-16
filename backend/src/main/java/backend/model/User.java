package backend.model;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;
    @Column(unique = true)
    private String userName;
    @Column
    private String password;
    @OneToOne(mappedBy = "theUser", cascade = CascadeType.ALL)
    private UserDetails userDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userdetails: first name="+ userDetails.getFirstName() + ", last name="+ userDetails.getLastName() + ", email="+ userDetails.getEmail() + ", phone number="+ userDetails.getPhoneNumber() +
                '}';
    }
}

