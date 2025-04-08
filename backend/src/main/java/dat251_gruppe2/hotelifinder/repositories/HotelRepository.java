package dat251_gruppe2.hotelifinder.repositories;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	Hotel findByName(String name);

	Hotel findByPostalCode(String postalCode);
}
