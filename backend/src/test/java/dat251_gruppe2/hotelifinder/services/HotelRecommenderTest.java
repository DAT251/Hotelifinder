package dat251_gruppe2.hotelifinder.services;
import dat251_gruppe2.hotelifinder.domain.Activity;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HotelRecommenderTest {

    @Test
    void testRecommendHotels_SingleActivity() {
        Activity hiking = new Activity("Hiking", new Location(59.9139, 10.7522));
        Hotel hotel1 = new Hotel("Vetles hus", new Location(59.9139, 10.7522));
        Hotel hotel2 = new Hotel("Magnus hus", new Location(59.9200, 10.7600));

        List<Hotel> hotels = List.of(hotel1, hotel2);
        HotelRecommender recommender = new HotelRecommender(hotels);

        List<Activity> selectedActivities = List.of(hiking);
        List<Hotel> recommendedHotels = recommender.recommendHotels(selectedActivities);

        assertEquals(2, recommendedHotels.size()); // Both hotels should be recommended
        assertTrue(recommendedHotels.contains(hotel1));
        assertTrue(recommendedHotels.contains(hotel2));

        // Since hotel1 is at the activity location, its travel time should be minimal or zero.
        assertTrue(recommendedHotels.stream().anyMatch(hotel -> hotel.getName().equals("Vetles hus") && hotel.getTotalTravelTime() <= 1));

    }

    @Test
    void testRecommendHotels_MultipleActivities() {
        Activity hiking = new Activity("Hiking", new Location(59.9139, 10.7522));
        Activity swimming = new Activity("Swimming", new Location(59.9145, 10.7510));
        Activity sightseeing = new Activity("Sightseeing", new Location(59.9200, 10.7600));

        Hotel hotel1 = new Hotel("Vetles hus", new Location(59.9139, 10.7522));
        Hotel hotel2 = new Hotel("Magnus hus", new Location(59.9200, 10.7600));

        List<Hotel> hotels = List.of(hotel1, hotel2);
        HotelRecommender recommender = new HotelRecommender(hotels);

        List<Activity> selectedActivities = List.of(hiking, swimming, sightseeing);
        List<Hotel> recommendedHotels = recommender.recommendHotels(selectedActivities);

        assertEquals(2, recommendedHotels.size()); // Both hotels should be recommended
        assertTrue(recommendedHotels.contains(hotel1));
        assertTrue(recommendedHotels.contains(hotel2));

        //Verify that the total travel time is calculated.
        assertTrue(recommendedHotels.stream().allMatch(hotel -> hotel.getTotalTravelTime() >= 0));
    }


    @Test
    void testRecommendHotels_EmptyHotelList() {
        List<Hotel> hotels = List.of();
        HotelRecommender recommender = new HotelRecommender(hotels);

        Activity hiking = new Activity("Hiking", new Location(59.9139, 10.7522));
        List<Activity> selectedActivities = List.of(hiking);
        List<Hotel> recommendedHotels = recommender.recommendHotels(selectedActivities);

        assertTrue(recommendedHotels.isEmpty()); // Should return an empty list when no hotels are available.
    }

}
