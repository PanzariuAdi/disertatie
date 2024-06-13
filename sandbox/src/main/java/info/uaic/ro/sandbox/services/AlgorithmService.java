package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlgorithmService {

    private final GraphRepository graphRepository;
    private final StatisticsService statisticsService;

    public Statistics createStatisticsFor(String code) {
        List<TestInput> inputs = graphRepository.getAllInputs();
        return statisticsService.createStatistics(code, inputs);
    }
}
