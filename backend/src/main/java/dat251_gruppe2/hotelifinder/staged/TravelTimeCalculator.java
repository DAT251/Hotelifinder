package dat251_gruppe2.hotelifinder.staged;

import dat251_gruppe2.hotelifinder.domain.Location;

// not in use
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
