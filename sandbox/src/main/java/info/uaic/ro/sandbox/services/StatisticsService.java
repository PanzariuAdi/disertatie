package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.models.Result;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticsService {

    private final RunnerService runnerService;
    private final GraphRepository graphRepository;
    private final List<String> testInputs = List.of("0", "107", "348", "414", "686", "698", "1684", "1912", "3437", "3980");

    public StatisticsService(RunnerService runnerService, GraphRepository graphRepository) {
        this.runnerService = runnerService;
        this.graphRepository = graphRepository;
    }

    public Statistics createStatistics(String algorithm) {
        Statistics statistics = new Statistics();

        testInputs.forEach(ts -> {
            TestInput testInput = graphRepository.getFacebookTestInput(ts);

            long start = System.currentTimeMillis();
            Object result = runnerService.runCode(algorithm, testInput.getGraph());
            long duration = System.currentTimeMillis() - start;
            createAndAddResultToStatistics(statistics, result, duration);
        });

        return statistics;
    }

    private void createAndAddResultToStatistics(Statistics statistics, Object object, long duration) {
        Result result = new Result();
        result.setActual(object);
        result.setDuration(duration);
        result.setMemory(0);

        statistics.addResult(result);
    }

}
