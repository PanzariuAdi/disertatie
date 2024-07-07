package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.exceptions.InvalidCodeException;
import info.uaic.ro.sandbox.exceptions.TimeExceededException;
import info.uaic.ro.sandbox.models.CodeRequest;
import info.uaic.ro.sandbox.models.Result;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.utils.MeasureUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
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

    @SneakyThrows
    public Result getResult(String code, TestInput input) {
        final long TIMEOUT = 15; // Timeout duration in minutes
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Callable<Object> task = () -> runnerService.runCode(code, input.getGraph());
        Future<Object> future = executor.submit(task);

        long start = System.currentTimeMillis();
        Object actual = null;

        try {
            actual = future.get(TIMEOUT, TimeUnit.MINUTES); // Attempt to get the result within the timeout
        } catch (TimeoutException e) {
            future.cancel(true); // Cancel the task if it times out
            throw new TimeExceededException();
        } catch (ExecutionException e ) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }


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
