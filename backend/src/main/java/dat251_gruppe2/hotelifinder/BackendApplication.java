package dat251_gruppe2.hotelifinder;

import dat251_gruppe2.hotelifinder.domain.*;
import dat251_gruppe2.hotelifinder.repositories.HotelRepository;
import dat251_gruppe2.hotelifinder.repositories.UserRepository;
import dat251_gruppe2.hotelifinder.services.HotelService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

	private final HotelService hotelService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HotelRepository hotelRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	public BackendApplication(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@PostConstruct
	private void initDatabase() {
		User user1 = new User("user1", "password");
		User user2 = new User("user2", "password");

		userRepository.saveAll(List.of(user1, user2));

		hotelRepository.saveAll(hotelService.getAllHotels());
	}
}
