package info.uaic.ro.backend.services;

import info.uaic.ro.backend.clients.SandboxClient;
import info.uaic.ro.backend.models.dto.CaseResult;
import info.uaic.ro.backend.models.dto.SandboxCaseResult;
import info.uaic.ro.backend.models.dto.SandboxResult;
import info.uaic.ro.backend.models.dto.Statistics;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.services.handlers.TestCaseHandler;
import info.uaic.ro.backend.services.handlers.TestCaseHandlerRegistry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ComparisonService {

    private final SandboxClient sandboxClient;
    private final TestCaseService testCaseService;

    public Statistics getStatistics(String code, String algorithmType, boolean isRun) {
        SandboxResult<?> sandboxResult = sandboxClient.getResultFor(code, algorithmType, isRun);
        List<TestCase> expectedValues = testCaseService.findAllBy(algorithmType, isRun);

        List<CaseResult<?>> caseResultList = generateCaseResults(sandboxResult, expectedValues);
        return buildStatistics(caseResultList);
    }

    private List<CaseResult<?>> generateCaseResults(SandboxResult<?> sandboxResult, List<TestCase> expected) {
        return IntStream.range(0, Math.min(sandboxResult.getResults().size(), expected.size()))
                .mapToObj(i -> createCaseResult(sandboxResult, expected, i))
                .collect(Collectors.toList());
    }

    private CaseResult<?> createCaseResult(SandboxResult<?> sandboxResult, List<TestCase> expected, int index) {
        TestCase testCase = expected.get(index);
        SandboxCaseResult<?> sandboxCaseResult = sandboxResult.getResults().get(index);
        return getHandler(testCase)
                .map(handler -> handler.createCaseResult(testCase, sandboxCaseResult))
                .orElseThrow(() -> new IllegalArgumentException("No handler registered for " + testCase.getClass()));
    }

    @SuppressWarnings("unchecked")
    private Optional<TestCaseHandler<TestCase>> getHandler(TestCase testCase) {
        return Optional.ofNullable((TestCaseHandler<TestCase>) TestCaseHandlerRegistry.getHandler(testCase.getClass()));
    }

    private Statistics buildStatistics(List<CaseResult<?>> caseResultList) {
        return Statistics.builder()
                .totalCases(caseResultList.size())
                .caseResultList(caseResultList)
                .build();
    }

}
