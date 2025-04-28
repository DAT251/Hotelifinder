package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import dat251_gruppe2.hotelifinder.domain.Venue;

public class RawDistanceTravelTime implements TravelTimeCalculator {

    /**
     * This is a primitive implementation for prototyping / testing purposes.
     * The plan for prod is to implement a new one that calls the google maps
     * distance api.
     */
    public RawDistanceTravelTime() {
    }

    @Override
    public Integer calculateTravelTime(Hotel hotel, Venue venue) {
        Location origin = hotel.getLocation();
        Location destination = venue.getLocation();

        // Calculate the difference in latitude and longitude
        double latDifference = Math.abs(origin.getLatitude() - destination.getLatitude());
        double lonDifference = Math.abs(origin.getLongitude() - destination.getLongitude());

        // Scale the differences to make the travel times more realistic
        // 1 degree of latitude ≈ 111 km, 1 degree of longitude ≈ 111 km at the equator
        // For simplicity, we'll assume 1 degree ≈ 111 km
        double latDistance = latDifference * 111; // Convert to kilometers
        double lonDistance = lonDifference * 111; // Convert to kilometers

        // Calculate Euclidean distance (in kilometers)
        double distance = Math.sqrt(latDistance * latDistance + lonDistance * lonDistance);

        // Convert distance to travel time (assuming an average speed of 50 km/h)
        double speed = 50; // 50 km/h
        double travelTimeHours = distance / speed; // Travel time in hours
        int travelTimeSeconds = (int) (travelTimeHours * 3600); // Convert to seconds

        return travelTimeSeconds;
    }
}
