package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCasesRepository extends JpaRepository<TestCase, Long> {

    List<TestCase> findAllByAlgorithmTypeAndDatasetCategory(AlgorithmType algorithmType, String datasetCategory);

}
