package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.utils.GraphUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GraphRepository {

    private static final List<String> VERY_SMALL_DATASETS = List.of("0s", "1s", "2s", "3s", "4s");
    private static final List<String> SMALL_DATASETS = List.of("0m", "1m", "2m", "3m", "4m", "5m");
    private static final List<String> MID_DATASETS = List.of("6m", "7m", "8m", "9m");
    private static final List<String> LARGE_DATASETS = List.of("0l", "1l", "2l");
    private static final List<String> VERY_LARGE_DATASETS = List.of("3l", "4l", "5l");

    private final List<TestInput> verySmallDatasets;
    private final List<TestInput> smallDatasets;
    private final List<TestInput> midDatasets;
    private final List<TestInput> largeDatasets;
    private final List<TestInput> veryLargeDatasets;

    public GraphRepository() {
        this.verySmallDatasets = new ArrayList<>();
        this.smallDatasets = new ArrayList<>();
        this.midDatasets = new ArrayList<>();
        this.largeDatasets = new ArrayList<>();
        this.veryLargeDatasets = new ArrayList<>();

        VERY_SMALL_DATASETS.forEach(ds -> verySmallDatasets.add(getTestInput(ds)));
        SMALL_DATASETS.forEach(ds -> smallDatasets.add(getTestInput(ds)));
        MID_DATASETS.forEach(ds -> midDatasets.add(getTestInput(ds)));
        LARGE_DATASETS.forEach(ds -> largeDatasets.add(getTestInput(ds)));
        VERY_LARGE_DATASETS.forEach(ds -> veryLargeDatasets.add(getTestInput(ds)));
    }

    public List<TestInput> getInputsFor(String dataset) {
        return switch (dataset) {
            case "verySmall" -> verySmallDatasets;
            case "small" -> smallDatasets;
            case "mid" -> midDatasets;
            case "large" -> largeDatasets;
            default -> veryLargeDatasets;
        };
    }

    private static TestInput getTestInput(String id) {
        String path = "/datasets/facebook/" + id + ".edges";

        return TestInput.builder()
                .id(id)
                .graph(GraphUtils.createGraphFromPath(path))
                .build();
    }
}
