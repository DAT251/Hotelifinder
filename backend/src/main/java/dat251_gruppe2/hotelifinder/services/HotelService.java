package dat251_gruppe2.hotelifinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat251_gruppe2.hotelifinder.domain.Hotel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HotelService {
    private final List<Hotel> hotels;

    public HotelService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("hotels.json");
        this.hotels = objectMapper.readValue(resource.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Hotel.class));
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
