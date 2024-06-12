package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlgorithmTypeRepository extends JpaRepository<AlgorithmType, Long> {

    Optional<AlgorithmType> findByName(String name);

}
