package dat251_gruppe2.hotelifinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat251_gruppe2.hotelifinder.domain.Location;
import dat251_gruppe2.hotelifinder.dto.HotelDistanceData;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonDistanceTravelTime {
    private final Map<String, Map<String, Integer>> travelTimes;

    public JsonDistanceTravelTime(String jsonFilePath, List<String> selectedVenueNames) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HotelDistanceData data = mapper.readValue(
                new ClassPathResource(jsonFilePath).getInputStream(),
                HotelDistanceData.class
        );

        this.travelTimes = new HashMap<>();

        for (HotelDistanceData.HotelWithDistances hotel : data.getHotels()) {
            Map<String, Integer> venueTimes = hotel.getDistances().stream()
                    .filter(distance -> selectedVenueNames.contains(distance.getVenue()))
                    .collect(Collectors.toMap(
                            HotelDistanceData.VenueDistance::getVenue,
                            HotelDistanceData.VenueDistance::getDurationSeconds
                    ));

            travelTimes.put(hotel.getName(), venueTimes);
        }
    }

    public Integer getTravelTime(String hotelName, String venueName) {
        return travelTimes.getOrDefault(hotelName, new HashMap<>())
                .getOrDefault(venueName, 0);
    }
}