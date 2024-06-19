package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {

    List<Dataset> findByDirectedAndWeighted(boolean directed, boolean weighted);
    List<Dataset> findByAlgorithms(AlgorithmType algorithmType);

}
