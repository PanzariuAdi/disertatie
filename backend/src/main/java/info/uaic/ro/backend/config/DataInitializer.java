package info.uaic.ro.backend.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.AnotherTestCase;
import info.uaic.ro.backend.models.entities.BetwennesCentralityTestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final TestCasesRepository testCasesRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;

    @PostConstruct
    public void initData() throws JsonProcessingException {
        AlgorithmType bc = new AlgorithmType();
        bc.setName("centrality");
        algorithmTypeRepository.save(bc);

        AlgorithmType at = new AlgorithmType();
        at.setName("another_type");
        algorithmTypeRepository.save(at);

        Map<Integer, Double> map = new HashMap<>();
        map.put(0, 0.0);
        map.put(1, 0.0);
        map.put(2, 0.0);
        map.put(3, 0.3);
        map.put(4, 0.3);
        map.put(5, 0.267);
        map.put(6, 0.0);
        map.put(7, 0.0);

        // CASE 0
        BetwennesCentralityTestCase bcTs0 = new BetwennesCentralityTestCase();
        bcTs0.setAlgorithmType(bc);
        bcTs0.setCaseNumber(0);
        bcTs0.setExpected(map);
        bcTs0.setDuration(10);
        bcTs0.setMemory(10);
        testCasesRepository.save(bcTs0);

        // CASE 0
        BetwennesCentralityTestCase bcTs1 = new BetwennesCentralityTestCase();
        bcTs1.setAlgorithmType(bc);
        bcTs1.setCaseNumber(1);
        bcTs1.setExpected(map);
        bcTs1.setDuration(10);
        bcTs1.setMemory(10);
        testCasesRepository.save(bcTs1);

        // CASE 0
        BetwennesCentralityTestCase bcTs2 = new BetwennesCentralityTestCase();
        bcTs2.setAlgorithmType(bc);
        bcTs2.setCaseNumber(2);
        bcTs2.setExpected(map);
        bcTs2.setDuration(10);
        bcTs2.setMemory(10);
        testCasesRepository.save(bcTs2);

        // CASE 0
        BetwennesCentralityTestCase bcTs3 = new BetwennesCentralityTestCase();
        bcTs3.setAlgorithmType(bc);
        bcTs3.setCaseNumber(3);
        bcTs3.setExpected(map);
        bcTs3.setDuration(10);
        bcTs3.setMemory(10);
        testCasesRepository.save(bcTs3);

        // CASE 0
        BetwennesCentralityTestCase bcTs4 = new BetwennesCentralityTestCase();
        bcTs4.setAlgorithmType(bc);
        bcTs4.setCaseNumber(4);
        bcTs4.setExpected(map);
        bcTs4.setDuration(10);
        bcTs4.setMemory(10);
        testCasesRepository.save(bcTs4);

        // CASE 0
        BetwennesCentralityTestCase bcTs5 = new BetwennesCentralityTestCase();
        bcTs5.setAlgorithmType(bc);
        bcTs5.setCaseNumber(5);
        bcTs5.setExpected(map);
        bcTs5.setDuration(10);
        bcTs5.setMemory(10);
        testCasesRepository.save(bcTs5);

        // CASE 0
        BetwennesCentralityTestCase bcTs6 = new BetwennesCentralityTestCase();
        bcTs6.setAlgorithmType(bc);
        bcTs6.setCaseNumber(6);
        bcTs6.setExpected(map);
        bcTs6.setDuration(10);
        bcTs6.setMemory(10);
        testCasesRepository.save(bcTs6);

        // CASE 0
        BetwennesCentralityTestCase bcTs7 = new BetwennesCentralityTestCase();
        bcTs7.setAlgorithmType(bc);
        bcTs7.setCaseNumber(7);
        bcTs7.setExpected(map);
        bcTs7.setDuration(10);
        bcTs7.setMemory(10);
        testCasesRepository.save(bcTs7);

        // CASE 0
        BetwennesCentralityTestCase bcTs8 = new BetwennesCentralityTestCase();
        bcTs8.setAlgorithmType(bc);
        bcTs8.setCaseNumber(8);
        bcTs8.setExpected(map);
        bcTs8.setDuration(10);
        bcTs8.setMemory(10);
        testCasesRepository.save(bcTs8);

        // CASE 0
        BetwennesCentralityTestCase bcTs9 = new BetwennesCentralityTestCase();
        bcTs9.setAlgorithmType(bc);
        bcTs9.setCaseNumber(9);
        bcTs9.setExpected(map);
        bcTs9.setDuration(10);
        bcTs9.setMemory(10);
        testCasesRepository.save(bcTs9);

        // ANOTHER TYPE
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // CASE 0
        AnotherTestCase aTc0 = new AnotherTestCase();
        aTc0.setAlgorithmType(at);
        aTc0.setCaseNumber(0);
        aTc0.setExpected(list);
        aTc0.setDuration(10);
        aTc0.setMemory(10);
        testCasesRepository.save(aTc0);
        testCasesRepository.save(aTc0);
    }
}
