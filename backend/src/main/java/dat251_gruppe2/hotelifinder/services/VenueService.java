package dat251_gruppe2.hotelifinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat251_gruppe2.hotelifinder.domain.Venue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VenueService {
    private final List<Venue> venues;

    public VenueService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("venues.json");
        this.venues = objectMapper.readValue(resource.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Venue.class));
    }

    public List<Venue> getAllVenues() {
        return venues;
    }

    public Venue getVenueByName(String name) {
        if (name == "") {
            return null;
        }
        for (Venue venue : venues) {
            if (venue.getName().equals(name)) {
                return venue;
            }
        }
        return null;
    }

    public Venue getVenueById(int index) {
        if (index < 0 || index >= venues.size()) {
            return null;
        }
        return venues.get(index);
    }
}
