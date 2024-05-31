package info.uaic.ro.backend.services;

import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestCaseService {

    private final TestCasesRepository testCasesRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;

    public List<TestCase> findAllBy(String algorithmType, boolean isRun) {
        return algorithmTypeRepository.findByName(algorithmType)
                .map(type -> testCasesRepository.findAllByAlgorithmTypeAndIsRun(type, isRun)
                        .stream()
                        .sorted(Comparator.comparingInt(TestCase::getCaseNumber))
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

}
