package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Location;

public interface TravelTimeCalculator {
    /**
     * Get the travel time between two locations.
     *
     * @param origin      The origin location.
     * @param destination The destination location.
     * @return Simulated travel time in seconds.
     */
    Integer calculateTravelTime(Location origin, Location destination);
}
