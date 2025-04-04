package dat251_gruppe2.hotelifinder.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.User;
import dat251_gruppe2.hotelifinder.domain.Venue;

/**
 * MockDataInstantiator
 */
public class MockDataInstantiator {

    private static final String HOTELS = """
            Citybox Oslo, 59.913818, 10.738740
            Scandic Vulkan, 59.922505, 10.751979
            """;

    private static final String USERS = """
            user1, password
            user2, password
            """;

    List<Hotel> hotels;
    List<Venue> venues;
    List<User> users;

    public MockDataInstantiator() {
        this.hotels = instantiateHotels();
        this.venues = instantiateVenues();
        this.users = instantiateUsers();
    }

    public Collection<Hotel> getHotels() {
        return this.hotels;
    }

    public Collection<Venue> getVenues() {
        return this.venues;
    }

    public Collection<User> getUsers() {
        return this.users;
    }

    private List<Hotel> instantiateHotels() {
        List<Hotel> hotels = new ArrayList<>();
        return hotels;
    }

    private List<Venue> instantiateVenues() {
        List<Venue> venues = new ArrayList<>();
        return venues;
    }

    private List<User> instantiateUsers() {
        List<User> users = new ArrayList<>();
        return users;
    }

}
