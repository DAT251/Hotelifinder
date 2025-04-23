package dat251_gruppe2.hotelifinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat251_gruppe2.hotelifinder.domain.Address;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import dat251_gruppe2.hotelifinder.dto.HotelDistanceData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final List<Hotel> hotels;

    public HotelService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("hotels.json");

        // Read using the DTO
        HotelDistanceData data = objectMapper.readValue(
                resource.getInputStream(),
                HotelDistanceData.class
        );

        // Convert to domain Hotel objects
        this.hotels = data.getHotels().stream()
                .map(jsonHotel -> {
                    Hotel hotel = new Hotel();
                    hotel.setName(jsonHotel.getName());

                    // Convert location
                    Location location = new Location();
                    location.setLatitude(jsonHotel.getLocation().getLat());
                    location.setLongitude(jsonHotel.getLocation().getLng());
                    hotel.setLocation(location);

                    // Convert address
                    Address address = new Address();
                    address.setStreetName(jsonHotel.getAddress().getStreetName());
                    address.setStreetNumber(jsonHotel.getAddress().getStreetNumber());
                    address.setPostalCode(jsonHotel.getAddress().getPostalCode());
                    hotel.setAddress(address);

                    return hotel;
                })
                .collect(Collectors.toList());
    }

    public List<Hotel> getAllHotels() {
        return hotels;
    }

    public Hotel getHotelById(int index) {
        if (index < 0 || index >= hotels.size()) {
            return null;
        }
        return hotels.get(index);
    }
}
