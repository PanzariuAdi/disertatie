package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlgorithmTypeRepository extends JpaRepository<AlgorithmType, UUID> {

    Optional<AlgorithmType> findByName(String name);

}
