package dat251_gruppe2.hotelifinder;

import dat251_gruppe2.hotelifinder.domain.*;
import dat251_gruppe2.hotelifinder.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {


		Activity hiking = new Activity("Hiking", new Location(59.9139, 10.7522));
		Activity swimming = new Activity("Swimming", new Location(59.9145, 10.7510));
		Activity sightseeing = new Activity("Sightseeing", new Location(59.9200, 10.7600));

		// Create some hotels
		Hotel hotel1 = new Hotel("Vetles hus", new Location(59.9139, 10.7522));
		Hotel hotel2 = new Hotel("Magnus hus", new Location(59.9200, 10.7600));

		List<Hotel> hotels = List.of(hotel1, hotel2);

		// Create a HotelRecommender
		HotelRecommender recommender = new HotelRecommender(hotels);

		// User selects activities
		List<Activity> selectedActivities = List.of(hiking, swimming, sightseeing);

		// Get recommended hotels
		List<Hotel> recommendedHotels = recommender.recommendHotels(selectedActivities);

		// Print recommended hotels
		for (Hotel hotel : recommendedHotels) {
			System.out.println(hotel.getName() + " - Total Travel Time: " + hotel.getTotalTravelTime() + " seconds");
		}
		//SpringApplication.run(BackendApplication.class, args);
	}



	@PostConstruct
	private void initDatabase() {
		User user1 = new User("user1", "password");
		User user2 = new User("user2", "password");

		userRepository.saveAll(List.of(user1, user2));

	}
}
