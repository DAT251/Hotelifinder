package dat251_gruppe2.hotelifinder.repositories;
import dat251_gruppe2.hotelifinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
