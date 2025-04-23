package dat251_gruppe2.hotelifinder.controllers;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Venue;
import dat251_gruppe2.hotelifinder.repositories.HotelRepository;
import dat251_gruppe2.hotelifinder.services.HotelRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recommender")
@CrossOrigin
public class HotelRecommenderController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/best")
    public ResponseEntity<Hotel> getBestHotel(@RequestBody List<Venue> venues) throws IOException {
        List<Hotel> hotels = hotelRepository.findAll();
        HotelRecommender recommender = new HotelRecommender(hotels, venues);
        Hotel bestMatch = recommender.getBestHotel();
        return ResponseEntity.ok(bestMatch);
    }

    @PostMapping
    public ResponseEntity<List<Hotel>> getRecommendations(@RequestBody List<Venue> venues) throws IOException {
        List<Hotel> hotels = hotelRepository.findAll();
        HotelRecommender recommender = new HotelRecommender(hotels, venues);
        List<Hotel> bestHotels = recommender.getHotels();
        System.out.println(bestHotels);
        return ResponseEntity.ok(bestHotels);
    }
}