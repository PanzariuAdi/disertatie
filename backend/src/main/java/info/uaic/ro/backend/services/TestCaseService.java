package info.uaic.ro.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.uaic.ro.backend.models.dto.RunEvaluationComparison;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TestCaseService {

    private final TestCasesRepository testCasesRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;
    private final ObjectMapper objectMapper;

    public Map<String, RunEvaluationComparison<?>> findAllBy(String algorithmType, String datasetCategory) {
        Map<String, RunEvaluationComparison<?>> map = new HashMap<>();

        algorithmTypeRepository.findByName(algorithmType)
                .ifPresent(type -> {
                    testCasesRepository.findAllByAlgorithmTypeAndDatasetCategory(type, datasetCategory)
                            .forEach(testCase -> {
                                RunEvaluationComparison<?> mappedTestRunEvaluationComparison = mapFrom(testCase);
                                map.put(testCase.getDataset(), mappedTestRunEvaluationComparison);
                            });
                });

        return map;
    }

    private RunEvaluationComparison<?> mapFrom(TestCase expectedTestCase) {
        return RunEvaluationComparison.builder()
                .expected(convert(expectedTestCase.getExpected()))
                .expectedDuration(expectedTestCase.getDuration())
                .expectedMemory(expectedTestCase.getMemory())
                .build();
    }

    private Object convert(String expected) {
        try {
            return objectMapper.readValue(expected, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error at de-serialize object!");
        }
    }

}
