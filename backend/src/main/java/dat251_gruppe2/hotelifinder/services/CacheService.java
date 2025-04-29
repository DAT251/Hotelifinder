package dat251_gruppe2.hotelifinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat251_gruppe2.hotelifinder.domain.Distance;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.dto.DistanceCacheDTO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Write to cache as well as read

@Service
public class CacheService {
    private final HotelService hotelService;
    private final VenueService venueService;
    private final List<Hotel> hotels;
    private final HashMap<Hotel, List<Distance>> distances;

    public CacheService(HotelService hotelService, VenueService venueService) throws IOException {
        this.hotelService = hotelService;
        this.venueService = venueService;
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("distance-cache.json");

        distances = new HashMap<Hotel, List<Distance>>();

        // Read using the DTO
        DistanceCacheDTO data = objectMapper.readValue(
                resource.getInputStream(),
                DistanceCacheDTO.class);

        this.hotels = new ArrayList<>();
        for (DistanceCacheDTO.HotelWithDistances hotelWithDistances : data.getHotels()) {
            Hotel hotel = hotelService.getHotelByName(hotelWithDistances.getName());
            hotels.add(hotel);
            List<Distance> dists = hotelWithDistances.getDistances().stream().map(
                    jsonDistance -> {
                        Distance distance = new Distance();
                        distance.setOrigin(hotel);
                        distance.setDestination(venueService.getVenueByName(jsonDistance.getVenue()));
                        distance.setDistanceCaption(jsonDistance.getDistanceText());
                        distance.setDurationCaption(jsonDistance.getDurationText());
                        distance.setDistance(jsonDistance.getDistanceMeters());
                        distance.setDistance(jsonDistance.getDurationSeconds());
                        return distance;
                    }).collect(Collectors.toList());
            this.distances.put(hotel, dists);
        }
    }

    public Boolean isCached(Hotel hotel) {
        return hotels.indexOf(hotel) != -1;
    }

    public List<Distance> getDistances(Hotel hotel) {
        return distances.getOrDefault(hotel, new ArrayList<>());
    }

    public Distance getDistance(Hotel hotel, Venue venue) {
        List<Distance> distances = this.getDistances(hotel);
        for (Distance distance : distances) {
            if (distance.getDestination().equals(venue)) {
                return distance;
            }
        }
        return null;
    }
}
