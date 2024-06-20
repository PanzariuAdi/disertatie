package info.uaic.ro.backend.services;

import info.uaic.ro.backend.models.dto.CaseResultList;
import info.uaic.ro.backend.models.dto.CodeRequest;
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

    public StatisticsDto getStatistics(CodeRequest codeRequest, String algorithmType) {
        Map<String, SandboxCaseDto<?>> actualMap = sandboxService.getActualMap(codeRequest);
        Map<String, CaseResultList<?>> expectedMap = testCaseService.findAllBy(algorithmType);

        List<CaseResultList<?>> caseResultList = getCaseResultList(actualMap, expectedMap);

        return StatisticsDto.builder()
                .totalCases(caseResultList.size())
                .caseResultList(caseResultList)
                .build();
    }

    private List<CaseResultList<?>> getCaseResultList(Map<String, SandboxCaseDto<?>> actualMap, Map<String, CaseResultList<?>> expectedMap) {
        return actualMap.entrySet().stream()
                .map(entry -> {
                    String dataset = entry.getKey();

                    CaseResultList<?> expected = expectedMap.get(dataset);
                    SandboxCaseDto<?> actual = entry.getValue();

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
