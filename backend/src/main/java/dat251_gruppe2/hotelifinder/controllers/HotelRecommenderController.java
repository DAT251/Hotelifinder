package dat251_gruppe2.hotelifinder.controllers;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.repositories.HotelRepository;
import dat251_gruppe2.hotelifinder.services.HotelRecommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Shitty langt navn, bytt det pls
 * HotelRecommenderController
 */
@RestController
@RequestMapping("/api/v1/recommender") // Bytt til konvensjonell URI
@CrossOrigin
public class HotelRecommenderController {

    @Autowired
    HotelRepository hotelRepository;

    public HotelRecommenderController() {
    }

    @PostMapping("/best")
    public ResponseEntity<Hotel> getBestHotel(@RequestBody List<Venue> venues) {
        List<Hotel> hotels = hotelRepository.findAll();

        HotelRecommender recommender = new HotelRecommender(hotels, venues);

        Hotel bestMatch = recommender.getBestHotel();

        return ResponseEntity.ok(bestMatch);
    }

    @PostMapping
    public ResponseEntity<List<Hotel>> getRecommendations(@RequestBody List<Venue> venues) {
        List<Hotel> hotels = hotelRepository.findAll();

        HotelRecommender recommender = new HotelRecommender(hotels, venues);

        List<Hotel> bestHotels = recommender.getHotels();

        return ResponseEntity.ok(bestHotels);
    }
}
