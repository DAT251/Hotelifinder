package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Activity;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HotelRecommenderTest {

    private final Hotel hotel1 = new Hotel("Vetles hus", new Location(59.9139, 10.7522));
    private final Hotel hotel2 = new Hotel("Magnus hus", new Location(59.9200, 10.7600));

    private final Activity hiking = new Activity("Hiking", new Location(59.9139, 10.7522));
    private final Activity swimming = new Activity("Swimming", new Location(59.9145, 10.7510));
    private final Activity sightseeing = new Activity("Sightseeing", new Location(59.9200, 10.7600));

    private final List<Hotel> hotels = List.of(hotel1, hotel2);

    @Test
    void singleActivity() {
        List<Activity> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(this.hotels, selectedActivities);

        Hotel bestMatch = recommender.getBestHotel();

        // Since hotel1 is at the activity location, its travel time should be minimal
        // or zero.
        assertTrue(bestMatch.getName().equals("Vetles hus"));
        assertTrue(recommender.getTravelTime(bestMatch) <= 1);
    }

    @Test
    void allHotelsRecommended() {
        List<Activity> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(this.hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        assertEquals(2, recommendedHotels.size()); // Both hotels should be recommended
        assertTrue(recommendedHotels.contains(hotel1));
        assertTrue(recommendedHotels.contains(hotel2));
    }

    @Test
    void travelTimeAboveZero() {

        List<Activity> selectedActivities = List.of(hiking, swimming, sightseeing);
        HotelRecommender recommender = new HotelRecommender(this.hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        // Verify that the total travel time is calculated.
        assertTrue(recommendedHotels.stream().allMatch(hotel -> recommender.getTravelTime(hotel) >= 0));
    }

    @Test
    void emptyHotelList() {
        List<Hotel> hotels = List.of();
        List<Activity> selectedActivities = List.of(hiking);

        HotelRecommender recommender = new HotelRecommender(hotels, selectedActivities);

        List<Hotel> recommendedHotels = recommender.getHotels();

        assertTrue(recommendedHotels.isEmpty()); // Should return an empty list when no hotels are available.
    }
}
