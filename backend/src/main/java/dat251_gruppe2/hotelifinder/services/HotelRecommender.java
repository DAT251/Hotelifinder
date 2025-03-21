package dat251_gruppe2.hotelifinder.services;
import dat251_gruppe2.hotelifinder.domain.Activity;
import dat251_gruppe2.hotelifinder.domain.Hotel;

import java.util.*;

public class HotelRecommender {
    private List<Hotel> hotels;

    public HotelRecommender(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Recommends hotels based on the least total travel time to selected activities.
     *
     * @param selectedActivities The activities selected by the user.
     * @return A list of hotels sorted by total travel time (ascending order).
     */
    public List<Hotel> recommendHotels(List<Activity> selectedActivities) {
        List<Hotel> recommendedHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            int totalTravelTime = 0;
            for (Activity activity : selectedActivities) {
                int travelTime = TravelTimeCalculator.calculateTravelTime(hotel.getLocation(), activity.getLocation());// Google maps API HER
                totalTravelTime += travelTime;
            }
            hotel.setTotalTravelTime(totalTravelTime); // Store total travel time in the hotel object
            recommendedHotels.add(hotel);
        }

        // Sort hotels by total travel time (ascending order)
        recommendedHotels.sort(Comparator.comparingInt(Hotel::getTotalTravelTime));

        return recommendedHotels;
    }
}
