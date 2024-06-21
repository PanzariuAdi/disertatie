package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.models.CodeRequest;
import info.uaic.ro.sandbox.models.Result;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.utils.MeasureUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final RunnerService runnerService;
    private final GraphRepository graphRepository;

    public Statistics createStatistics(CodeRequest codeRequest) {
        Statistics statistics = new Statistics();

        codeRequest.getDatasets().forEach(dataset -> {
            TestInput testInput = graphRepository.getInputFor(dataset);
            statistics.addResult(getResult(codeRequest.getCode(), testInput));
        });

        return statistics;
    }

    public Result getResult(String code, TestInput input) {
        long start = System.currentTimeMillis();
        Object actual = runnerService.runCode(code, input.getGraph());
        long duration = System.currentTimeMillis() - start;

        long memoryUsed = MeasureUtils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        Result result = new Result();
        result.setActual(actual);
        result.setDuration(duration);
        result.setMemory(memoryUsed);
        result.setDataset(input.getDataset());
        result.setDatasetCategory(input.getDatasetCategory());

        return result;
    }

}
