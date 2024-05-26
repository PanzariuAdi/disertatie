package info.uaic.ro.backend.services;

import info.uaic.ro.backend.aop.LogFunctionCall;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ComparisonService {

    private final SandboxClient sandboxClient;
    private final TestCaseService testCaseService;

    public Statistics getStatistics(String code, String algorithmType, boolean isRun) {
        SandboxResult<?> result = sandboxClient.getResultFor(code, algorithmType);
        List<TestCase> expected = testCaseService.findAllByAlgorithmType(algorithmType);

        return combineResults(result, expected);
    }

    private Statistics combineResults(SandboxResult<?> sandboxResult, List<TestCase> expected) {
        List<SandboxCaseResult<?>> sortedResults = sandboxResult.getResults().stream()
                .sorted(Comparator.comparingInt(SandboxCaseResult::getCaseNumber))
                .collect(Collectors.toList());

        List<TestCase> sortedExpected = expected.stream()
                .sorted(Comparator.comparingInt(TestCase::getCaseNumber))
                .collect(Collectors.toList());

        if (sortedResults.size() != sortedExpected.size()) {
            throw new IllegalArgumentException("Test cases sizes do not match!");
        }

        List<CaseResult<?>> caseResultList = IntStream.range(0, sortedExpected.size())
                .mapToObj(i -> getCaseResult(sandboxResult, sortedExpected, i))
                .collect(Collectors.toList());

        return Statistics.builder()
                .totalCases(caseResultList.size())
                .caseResultList(caseResultList)
                .build();
    }

    private CaseResult<?> getCaseResult(SandboxResult<?> sandboxResult, List<TestCase> expected, int i) {
        TestCase testCase = expected.get(i);
        SandboxCaseResult<?> sandboxCaseResult = sandboxResult.getResults().get(i);

        TestCaseHandler<TestCase> handler = (TestCaseHandler<TestCase>) TestCaseHandlerRegistry.getHandler(testCase.getClass());

        if (handler == null) {
            throw new IllegalArgumentException("No handler registered for " + testCase.getClass());
        }

        return handler.createCaseResult(testCase, sandboxCaseResult);
    }

}
