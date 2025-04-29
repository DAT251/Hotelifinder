package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Address;
import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class HotelRecommenderTest {

    private final Hotel hotel1 = new Hotel("Vetles hus", new Location(59.9139, 10.7522), new Address());
    private final Hotel hotel2 = new Hotel("Magnus hus", new Location(59.9200, 10.7600), new Address());

    private Venue hiking = new Venue();
    private Venue swimming = new Venue();
    private Venue sightseeing = new Venue();

    private final List<Hotel> hotels = List.of(hotel1, hotel2);

    CacheService mockCacheService = mock(CacheService.class);

    HotelRecommenderTest() {
        this.hiking.setLocation(new Location(59.9139, 10.7522));
        this.swimming.setLocation(new Location(59.9145, 10.7510));
        this.sightseeing.setLocation(new Location(59.9200, 10.7600));
    }

    @Test
    void singleVenue() {

        List<Venue> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(mockCacheService, this.hotels, selectedActivities);

        Hotel bestMatch = recommender.getBestHotel();

        assertTrue(bestMatch.getName().equals("Vetles hus"));
        assertTrue(recommender.getTravelTime(bestMatch) <= 1);
    }

    @Test
    void allHotelsRecommended() {

        List<Venue> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(mockCacheService, this.hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        assertEquals(2, recommendedHotels.size());
        assertTrue(recommendedHotels.contains(hotel1));
        assertTrue(recommendedHotels.contains(hotel2));
    }

    @Test
    void travelTimeAboveZero() {

        List<Venue> selectedActivities = List.of(hiking, swimming, sightseeing);

        HotelRecommender recommender = new HotelRecommender(mockCacheService, this.hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        assertTrue(recommendedHotels.stream().allMatch(hotel -> recommender.getTravelTime(hotel) >= 0));
    }

    @Test
    void emptyHotelList() {

        List<Hotel> hotels = List.of();
        List<Venue> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(mockCacheService, hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        assertTrue(recommendedHotels.isEmpty());
    }
}
