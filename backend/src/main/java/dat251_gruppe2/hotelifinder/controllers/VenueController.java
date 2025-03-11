package dat251_gruppe2.hotelifinder.controllers;


import dat251_gruppe2.hotelifinder.domain.Address;
import dat251_gruppe2.hotelifinder.domain.Venue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        venue1.setImageURL("http://localhost:8080/images/vestkanten.jpg");
        Address address1 = new Address();
        address1.setStreetName("Loddefjordveien");
        address1.setStreetNumber(2);
        address1.setPostalCode(5171);
        venue1.setAddress(address1);

        Venue venue2 = new Venue();
        venue2.setName("Fana Golfbane");
        venue2.setImageURL("http://localhost:8080/images/fanagolf.jpg");
        Address address2 = new Address();
        address2.setStreetName("Vestre Rå");
        address2.setStreetNumber(82);
        address2.setPostalCode(5239);
        venue2.setAddress(address2);

        Venue venue3 = new Venue();
        venue3.setName("Lagunen");
        venue3.setImageURL("http://localhost:8080/images/lagunen.jpg");
        Address address3 = new Address();
        address3.setStreetName("Laguneveien");
        address3.setStreetNumber(3);
        address3.setPostalCode(5239);
        venue3.setAddress(address3);

        Venue venue4 = new Venue();
        venue4.setName("Oasen");
        venue4.setImageURL("http://localhost:8080/images/oasen.jpg");
        Address address4 = new Address();
        address4.setStreetName("Folke Bernadottes vei");
        address4.setStreetNumber(52);
        address4.setPostalCode(5147);
        venue4.setAddress(address4);

        venues.put(1L, venue1);
        venues.put(2L, venue2);
        venues.put(3L, venue3);
        venues.put(4L, venue4);
    }

   @GetMapping
   public ResponseEntity<Collection<Venue>> getVenues(){
       if (venues.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
       }

       return ResponseEntity.ok(venues.values());

   }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenues(@PathVariable Long id){
        if (!venues.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(venues.get(id));


    }



}
