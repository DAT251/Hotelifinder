package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.domain.Hotel;

import java.util.*;

public class HotelRecommender {
    /**
     * Input hotels
     */
    private List<Hotel> hotels;
    /**
     * Input activities
     */
    private List<Venue> selectedActivities;

    /**
     * Calculated recommendations.
     * This contains hotels with their total travel time.
     */
    private Map<Hotel, Integer> recommendations;
    /**
     * The same hotels as input, sorted by total travel time.
     */
    private List<Hotel> sortedHotels;

    /**
     * The strategy to calculate distance.
     * To be changed to Google API in production.
     */
    private TravelTimeCalculator travelTimeService = new RawDistanceTravelTime();

    public HotelRecommender(List<Hotel> hotels, List<Venue> selectedActivities) {
        this.hotels = hotels;
        this.selectedActivities = selectedActivities;

        this.recommendations = calculateRecommendations();
        this.sortedHotels = sortHotels(recommendations);
    }

    private Map<Hotel, Integer> calculateRecommendations() {
        HashMap<Hotel, Integer> recommendations = new HashMap<Hotel, Integer>();

        for (Hotel hotel : hotels) {
            int totalTravelTime = 0;
            for (Venue venue : selectedActivities) {
                Integer travelTime = travelTimeService.calculateTravelTime(
                        hotel.getLocation(),
                        venue.getLocation());
                totalTravelTime += travelTime;
            }
            recommendations.put(hotel, totalTravelTime);
        }

        return recommendations;
    }

    private List<Hotel> sortHotels(Map<Hotel, Integer> recommendations) {
        sortedHotels = new ArrayList<Hotel>(recommendations.keySet());
        sortedHotels.sort(Comparator.comparingInt(recommendations::get));
        return sortedHotels;
    }

    public Hotel getBestHotel() {
        Hotel hotel = this.sortedHotels.getFirst();
        return hotel;
    }

    /**
     * Get the recommended hotels.
     *
     * @return A list of hotels sorted by total travel time (ascending order).
     */
    public List<Hotel> getHotels() {
        return sortedHotels.stream().limit(3).toList();
    }

    public Integer getTravelTime(Hotel hotel) {
        return recommendations.get(hotel);
    }
}
