package dat251_gruppe2.hotelifinder;

import dat251_gruppe2.hotelifinder.domain.User;
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
		SpringApplication.run(BackendApplication.class, args);
	}



	@PostConstruct
	private void initDatabase() {
		User user1 = new User("user1", "password");
		User user2 = new User("user2", "password");

		userRepository.saveAll(List.of(user1, user2));

	}
}
