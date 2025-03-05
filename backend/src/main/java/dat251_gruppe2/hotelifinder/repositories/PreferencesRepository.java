package dat251_gruppe2.hotelifinder.repositories;
import dat251_gruppe2.hotelifinder.domain.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
}
