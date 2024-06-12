package info.uaic.ro.backend.services;

import info.uaic.ro.backend.models.dto.RunEvaluationComparison;
import info.uaic.ro.backend.models.dto.SandboxCaseDto;
import info.uaic.ro.backend.models.dto.StatisticsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComparisonService {

    private final SandboxService sandboxService;
    private final TestCaseService testCaseService;

    public StatisticsDto getStatistics(String code, String algorithmType, String datasetCategory) {
        Map<String, SandboxCaseDto<?>> actualMap = sandboxService.getActualMap(code, datasetCategory);
        Map<String, RunEvaluationComparison<?>> expectedMap = testCaseService.findAllBy(algorithmType, datasetCategory);

        List<RunEvaluationComparison<?>> runEvaluationComparisonResultList = getCaseResultList(actualMap, expectedMap);

        return StatisticsDto.builder()
                .totalCases(runEvaluationComparisonResultList.size())
                .runEvaluationComparisonResultList(runEvaluationComparisonResultList)
                .build();
    }

    private List<RunEvaluationComparison<?>> getCaseResultList(Map<String, SandboxCaseDto<?>> actualMap, Map<String, RunEvaluationComparison<?>> expectedMap) {
        return expectedMap.entrySet().stream()
                .map(entry -> {
                    String dataset = entry.getKey();
                    RunEvaluationComparison<?> expected = entry.getValue();
                    SandboxCaseDto<?> actual = actualMap.get(dataset);

                    return RunEvaluationComparison.builder()
                            .expected(expected.getExpected())
                            .expectedDuration(expected.getDuration())
                            .expectedMemory(expected.getMemory())
                            .actual(actual.getActual())
                            .duration(actual.getDuration())
                            .memory(actual.getMemory())
                            .correct(expected.getExpected().equals(actual.getActual()))
                            .dataset(dataset)
                            .build();
                })
                .sorted(Comparator.comparing(RunEvaluationComparison::getDataset))
                .collect(Collectors.toUnmodifiableList());
    }

}
