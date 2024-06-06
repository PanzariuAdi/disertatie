package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.models.Result;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.utils.MeasureUtils;
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

    public Statistics createStatistics(String code, String dataset) {
        Statistics statistics = new Statistics();

        List<TestInput> inputs = graphRepository.getInputsFor(dataset);

        inputs.forEach(input -> {
            long start = System.currentTimeMillis();
            Object result = runnerService.runCode(code, input.getGraph());
            System.out.println(result);
            long duration = System.currentTimeMillis() - start;

            long memoryUsed = MeasureUtils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            createAndAddResultToStatistics(statistics, result, duration, memoryUsed);
        });

        return statistics;
    }

    private void createAndAddResultToStatistics(Statistics statistics, Object object, long duration, long memoryUsed) {
        Result result = new Result();
        result.setActual(object);
        result.setDuration(duration);
        result.setMemory(memoryUsed);

        statistics.addResult(result);
    }

}
