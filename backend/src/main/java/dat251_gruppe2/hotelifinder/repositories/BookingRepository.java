package dat251_gruppe2.hotelifinder.repositories;
import dat251_gruppe2.hotelifinder.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
