package gogora.gogo.goplan.repository;

import gogora.gogo.goplan.entity.query.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findById(Long id);
}
