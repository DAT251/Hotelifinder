package dat251_gruppe2.hotelifinder;

import dat251_gruppe2.hotelifinder.domain.*;
import dat251_gruppe2.hotelifinder.repositories.HotelRepository;
import dat251_gruppe2.hotelifinder.repositories.UserRepository;
import dat251_gruppe2.hotelifinder.services.HotelRecommender;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HotelRepository hotelRepository;

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	private void initDatabase() {
		User user1 = new User("user1", "password");
		User user2 = new User("user2", "password");

		userRepository.saveAll(List.of(user1, user2));

		Hotel hotel1 = new Hotel("Citybox Oslo", new Location(59.913818, 10.738740));
		Hotel hotel2 = new Hotel("Scandic Vulkan", new Location(59.922505, 10.751979));

		hotelRepository.saveAll(List.of(hotel1, hotel2));

	}

}
