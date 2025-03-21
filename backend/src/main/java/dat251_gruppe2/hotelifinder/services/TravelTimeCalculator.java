package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Location;

public class TravelTimeCalculator {


    /**
     * Placeholder method to calculate travel time between two locations.
     * In a real implementation, this would call the Google Maps API.
     *
     * @param origin      The origin location.
     * @param destination The destination location.
     * @return Simulated travel time in seconds.
     */
    public static int calculateTravelTime(Location origin, Location destination) {
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
