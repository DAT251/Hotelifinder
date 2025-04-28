package dat251_gruppe2.hotelifinder.services;

import dat251_gruppe2.hotelifinder.domain.Distance;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Venue;

public class CacheTravelTime implements TravelTimeCalculator {

    private final CacheService cacheService;

    private TravelTimeCalculator travelTimeService = new RawDistanceTravelTime();

    public CacheTravelTime(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public Integer calculateTravelTime(Hotel hotel, Venue venue) {

        Integer travelTimeSeconds = 999999;

        // Try to fetch the distance from cache, null means miss
        Distance distance = cacheService.getDistance(hotel, venue);
        if (distance != null) {
            return distance.getDistanceSeconds();
        }

        // If the distance is not cached, use absolute distance
        travelTimeSeconds = travelTimeService.calculateTravelTime(hotel, venue);

        return travelTimeSeconds;
    }
}
