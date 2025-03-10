package dat251_gruppe2.hotelifinder.controllers;


import dat251_gruppe2.hotelifinder.domain.Address;
import dat251_gruppe2.hotelifinder.domain.Venue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/venues")
@CrossOrigin
public class VenueController {

    HashMap<Long, Venue> venues = new HashMap<>();

    public VenueController() {
        Venue venue1 = new Venue();
        venue1.setName("Vestkanten");
        Address address1 = new Address();
        address1.setStreetName("Loddefjordveien");
        address1.setStreetNumber(2);
        address1.setPostalCode(5171);
        venue1.setAddress(address1);

    }

   @GetMapping
   public ResponseEntity<Collection<Venue>>  getVenues(){
        return ResponseEntity.ok(venues.values());

   }


}
