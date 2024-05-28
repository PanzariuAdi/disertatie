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

    public StatisticsService(RunnerService runnerService, GraphRepository graphRepository) {
        this.runnerService = runnerService;
        this.graphRepository = graphRepository;
    }

    public Statistics createStatistics(String algorithm, String algorithmType, boolean isRun) {
        Statistics statistics = new Statistics();

        List<TestInput> inputs = graphRepository.getAllTestInputsAfter(algorithmType, isRun);

        inputs.forEach(input -> {
            long start = System.currentTimeMillis();
            Object result = runnerService.runCode(algorithm, input.getGraph());
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
