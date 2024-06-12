package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.models.Result;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.utils.MeasureUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final RunnerService runnerService;
    private final GraphRepository graphRepository;

    public Statistics createStatistics(String code, String datasetCategory) {
        Statistics statistics = new Statistics();

        List<TestInput> inputs = graphRepository.getInputsFor(datasetCategory);

        inputs.forEach(input -> {
            long start = System.currentTimeMillis();
            Object actual = runnerService.runCode(code, input.getGraph());
            long duration = System.currentTimeMillis() - start;

            long memoryUsed = MeasureUtils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            createAndAddResultToStatistics(statistics, actual, duration, memoryUsed, input.getDataset(), input.getDatasetCategory());
        });

        return statistics;
    }

    public Statistics createStatistics(String code, List<TestInput> inputs) {
        Statistics statistics = new Statistics();

        inputs.forEach(input -> {
            long start = System.currentTimeMillis();
            Object actual = runnerService.runCode(code, input.getGraph());
            long duration = System.currentTimeMillis() - start;

            long memoryUsed = MeasureUtils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            createAndAddResultToStatistics(statistics, actual, duration, memoryUsed, input.getDataset(), input.getDatasetCategory());
        });

        return statistics;
    }

    private void createAndAddResultToStatistics(Statistics statistics, Object object, long duration, long memoryUsed, String dataset, String datasetCategory) {
        Result result = new Result();
        result.setActual(object);
        result.setDuration(duration);
        result.setMemory(memoryUsed);
        result.setDataset(dataset);
        result.setDatasetCategory(datasetCategory);

        statistics.addResult(result);
    }

}
