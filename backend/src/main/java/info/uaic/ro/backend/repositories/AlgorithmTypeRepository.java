package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlgorithmTypeRepository extends JpaRepository<AlgorithmType, Long> {
    Optional<AlgorithmType> findByName(String name);
}
