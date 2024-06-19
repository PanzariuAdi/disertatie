package info.uaic.ro.backend.services;

import info.uaic.ro.backend.models.dto.CaseResultList;
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
        Map<String, CaseResultList<?>> expectedMap = testCaseService.findAllBy(algorithmType);

        List<CaseResultList<?>> caseResultListResultList = getCaseResultList(actualMap, expectedMap);

        return StatisticsDto.builder()
                .totalCases(caseResultListResultList.size())
                .caseResultList(caseResultListResultList)
                .build();
    }

    private List<CaseResultList<?>> getCaseResultList(Map<String, SandboxCaseDto<?>> actualMap, Map<String, CaseResultList<?>> expectedMap) {
        return expectedMap.entrySet().stream()
                .map(entry -> {
                    String dataset = entry.getKey();
                    CaseResultList<?> expected = entry.getValue();
                    SandboxCaseDto<?> actual = actualMap.get(dataset);

                    return CaseResultList.builder()
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
                .sorted(Comparator.comparing(CaseResultList::getDataset))
                .collect(Collectors.toUnmodifiableList());
    }

}
