package dat251_gruppe2.hotelifinder.controllers;


import dat251_gruppe2.hotelifinder.domain.Address;
import dat251_gruppe2.hotelifinder.domain.Venue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@CrossOrigin
public class VenueController {

    HashMap<Long, Venue> venues = new HashMap<>();

    public VenueController() {
        Venue venue1 = new Venue();
        venue1.setName("Oslo Sentrum");
        venue1.setImageURL("http://localhost:8080/images/vestkanten.jpg");
        Address address1 = new Address();
        address1.setStreetName("Jernbanetorget");
        address1.setStreetNumber(1);
        address1.setPostalCode("0154");
        venue1.setAddress(address1);
        List<String> tags1 = new ArrayList<>();
        tags1.add("Shopping");

        venue1.setTags(tags1);

        Venue venue2 = new Venue();
        venue2.setName("Holmenkollen");
        venue2.setImageURL("http://localhost:8080/images/fanagolf.jpg");
        Address address2 = new Address();
        address2.setStreetName("Kongeveien");
        address2.setStreetNumber(40);
        address2.setPostalCode("0747");
        venue2.setAddress(address2);
        List<String> tags2 = new ArrayList<>();
        tags2.add("Golf");
        venue2.setTags(tags2);

        Venue venue3 = new Venue();
        venue3.setName("Frogner");
        venue3.setImageURL("http://localhost:8080/images/lagunen.jpg");
        Address address3 = new Address();
        address3.setStreetName("Frogner plass");
        address3.setStreetNumber(1);
        address3.setPostalCode("0266");
        venue3.setAddress(address3);
        List<String> tags3 = new ArrayList<>();
        tags3.add("Shopping");
        tags3.add("Bowling");
        tags3.add("Cinema");
        venue3.setTags(tags3);

        Venue venue4 = new Venue();
        venue4.setName("Ullevål");
        venue4.setImageURL("http://localhost:8080/images/oasen.jpg");
        Address address4 = new Address();
        address4.setStreetName("Sognsveien");
        address4.setStreetNumber(75);
        address4.setPostalCode("0855");
        venue4.setAddress(address4);
        List<String> tags4 = new ArrayList<>();
        tags4.add("Shopping");
        tags4.add("Museum");
        venue4.setTags(tags4);


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
