package dat251_gruppe2.hotelifinder;

import dat251_gruppe2.hotelifinder.domain.*;
import dat251_gruppe2.hotelifinder.repositories.HotelRepository;
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
		Address address1 = new Address();
		address1.setStreetNumber(6);
		address1.setStreetName("Prinsens gate");
		address1.setPostalCode("0152");

		Address address2 = new Address();
		address2.setStreetNumber(13);
		address2.setStreetName("Maridalsveien");
		address2.setPostalCode("0178");

		Address address3 = new Address();
		address3.setStreetNumber(3);
		address3.setStreetName("Biskop Gunnerus gate");
		address3.setPostalCode("0155");

		Address address4 = new Address();
		address4.setStreetNumber(26);
		address4.setStreetName("Kongeveien");
		address4.setPostalCode("0787");


		Hotel hotel1 = new Hotel("Citybox Oslo", new Location(59.913818, 10.738740), address1);
		Hotel hotel2 = new Hotel("Scandic Vulkan", new Location(59.922505, 10.751979), address2);

		Hotel hotel3 = new Hotel("Clarion Hotel The Hub", new Location(59.91264337173403, 10.749970155823531), address3);
		Hotel hotel4 = new Hotel("Scandic Holmenkollen Park Hotel", new Location(59.962810881797445, 10.663046976705882), address4);

		hotelRepository.saveAll(List.of(hotel1, hotel2, hotel3, hotel4));

	}

}
