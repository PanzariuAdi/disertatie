package info.uaic.ro.backend.services.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.uaic.ro.backend.models.dto.CaseResult;
import info.uaic.ro.backend.models.dto.SandboxCaseResult;
import info.uaic.ro.backend.models.entities.BetwennesCentralityTestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BetweennessCentralityTestCaseHandler implements TestCaseHandler<BetwennesCentralityTestCase> {

    @Override
    @SuppressWarnings("unchecked")
    public CaseResult<Map<Integer, Double>> createCaseResult(BetwennesCentralityTestCase testCase, SandboxCaseResult<?> sandboxCaseResult) {
        CaseResult<Map<Integer, Double>> caseResult = new CaseResult<>();
        try {
            caseResult.setExpected(testCase.getExpected());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        caseResult.setActual(getActual(sandboxCaseResult));
        caseResult.setCaseNumber(testCase.getCaseNumber());
        caseResult.setMemory(sandboxCaseResult.getMemory());
        caseResult.setDuration(sandboxCaseResult.getDuration());
        caseResult.setExpectedMemory(testCase.getMemory());
        caseResult.setExpectedDuration(testCase.getDuration());
        try {
            caseResult.setCorrect(testCase.getExpected().equals(caseResult.getActual()));
        } catch (JsonProcessingException e) {
            log.error("Error at comparing actual with expected! {}", e.getMessage());
        }

        return caseResult;
    }

    private <T> Map<Integer, Double> getActual(SandboxCaseResult<T> sandboxCaseResult) {
        Map<Integer, Double> convertedMap = new HashMap<>();

        T actual = sandboxCaseResult.getActual();

        if (actual instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) actual;

            if (!map.isEmpty() && map.keySet().iterator().next() instanceof String) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    try {
                        Integer key = Integer.valueOf((String) entry.getKey());
                        Double value = (Double) entry.getValue();
                        convertedMap.put(key, value);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return (Map<Integer, Double>) actual;
            }
        }
        return convertedMap;
    }

}
