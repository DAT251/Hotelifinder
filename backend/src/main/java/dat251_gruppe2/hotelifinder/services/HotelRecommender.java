package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HotelRecommender {
    private final List<Hotel> hotels;
    private final List<Venue> selectedActivities;
    private final Map<Hotel, Integer> recommendations;
    private final List<Hotel> sortedHotels;

    public HotelRecommender(List<Hotel> hotels, List<Venue> selectedActivities) throws IOException {
        this.hotels = hotels;
        this.selectedActivities = selectedActivities;

        List<String> venueNames = selectedActivities.stream()
                .map(Venue::getName)
                .collect(Collectors.toList());

        JsonDistanceTravelTime travelTimeService = new JsonDistanceTravelTime("hotels.json", venueNames);
        this.recommendations = calculateRecommendations(travelTimeService);
        this.sortedHotels = sortHotels(recommendations);
    }

    private Map<Hotel, Integer> calculateRecommendations(JsonDistanceTravelTime travelTimeService) {
        Map<Hotel, Integer> recommendations = new HashMap<>();

        for (Hotel hotel : hotels) {
            int totalTravelTime = 0;
            for (Venue venue : selectedActivities) {
                totalTravelTime += travelTimeService.getTravelTime(hotel.getName(), venue.getName());
            }
            recommendations.put(hotel, totalTravelTime);
        }

        return recommendations;
    }

    // Rest of your existing methods remain the same...
    private List<Hotel> sortHotels(Map<Hotel, Integer> recommendations) {
        List<Hotel> sorted = new ArrayList<>(recommendations.keySet());
        sorted.sort(Comparator.comparingInt(recommendations::get));
        return sorted;
    }

    public Hotel getBestHotel() {
        return sortedHotels.isEmpty() ? null : sortedHotels.get(0);
    }

    public List<Hotel> getHotels() {
        return sortedHotels.stream().limit(3).toList();
    }

    public Integer getTravelTime(Hotel hotel) {
        return recommendations.get(hotel);
    }
}