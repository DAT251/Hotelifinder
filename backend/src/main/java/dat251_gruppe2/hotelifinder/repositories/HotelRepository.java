package dat251_gruppe2.hotelifinder.repositories;

import dat251_gruppe2.hotelifinder.domain.Hotel;
import dat251_gruppe2.hotelifinder.domain.Location;
import dat251_gruppe2.hotelifinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
    Hotel findByPostalCode(Integer postalCode);
}
