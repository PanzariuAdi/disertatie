package info.uaic.ro.backend.repositories;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestCasesRepository extends JpaRepository<TestCase, UUID> {

    List<TestCase> findAllByAlgorithmType(AlgorithmType algorithmType);

}
