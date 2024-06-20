package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DatasetRepository extends JpaRepository<Dataset, UUID> {

    List<Dataset> findByDirectedAndWeighted(boolean directed, boolean weighted);
    List<Dataset> findByAlgorithms(AlgorithmType algorithmType);
    Optional<Dataset> findByFileNameAndAlgorithms(String fileName, AlgorithmType algorithmType);

}
