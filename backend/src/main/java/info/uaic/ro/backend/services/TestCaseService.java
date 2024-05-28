package info.uaic.ro.backend.services;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestCaseService {

    private final TestCasesRepository testCasesRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;

    public List<TestCase> findAllBy(String algorithmType, boolean isRun) {
        Optional<AlgorithmType> optional = algorithmTypeRepository.findByName(algorithmType);

        if (optional.isEmpty()) {
            return new ArrayList<>();
        }

        return testCasesRepository.findAllByAlgorithmTypeAndIsRun(optional.get(), isRun);
    }

}
