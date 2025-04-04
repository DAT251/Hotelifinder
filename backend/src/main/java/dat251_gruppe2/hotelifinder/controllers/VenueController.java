package dat251_gruppe2.hotelifinder.controllers;

import dat251_gruppe2.hotelifinder.domain.Location;
import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/venues")
@CrossOrigin
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
public ResponseEntity<List<Venue>> getVenues(@RequestParam String city) {
    List<Venue> filteredVenues = venueService.getAllVenues().stream()
        .filter(venue -> venue.getCity().equalsIgnoreCase(city))
        .collect(Collectors.toList());

    if (filteredVenues.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.ok(filteredVenues);
}


    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable int id) {
        Venue venue = venueService.getVenueById(id - 1); // Adjust index for zero-based list
        if (venue == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(venue);
    }
}
