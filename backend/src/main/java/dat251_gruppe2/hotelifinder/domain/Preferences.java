package dat251_gruppe2.hotelifinder.domain;

import jakarta.persistence.*;

@Entity
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferredHotelType;
    private String preferredLocation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferredHotelType() {
        return preferredHotelType;
    }

    public void setPreferredHotelType(String preferredHotelType) {
        this.preferredHotelType = preferredHotelType;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
