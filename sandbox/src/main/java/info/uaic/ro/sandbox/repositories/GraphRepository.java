package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.utils.GraphUtils;
import info.uaic.ro.sandbox.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GraphRepository {

    private final List<String> datasets;
    private final int UNDIRECTED = 9;
    private final int DIRECTED  = 1;
    private final int WEIGHTED = 5;

    public GraphRepository() {
        datasets = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i < UNDIRECTED) datasets.add("undirected" + i);
            if (i < DIRECTED) datasets.add("directed" + i);
            if (i < WEIGHTED) datasets.add("weighted" + i);
        }
    }

    public List<TestInput> getAllInputs() {
        List<TestInput> inputs = new ArrayList<>();

        for (String s : datasets) {
            inputs.add(getTestInput(s));
        }

        return inputs;
    }

    public TestInput getInputFor(String dataset) {
        return getTestInput(dataset);
    }

    public List<TestInput> getInputs(List<String> datasets) {
        return datasets
                .stream()
                .map(this::getTestInput)
                .collect(Collectors.toList());
    }

    public List<TestInput> getInputsFor(String datasetCategory) {
       List<TestInput> inputs = new ArrayList<>();

       datasets.forEach(dataset -> {
           if (dataset.startsWith(datasetCategory)) inputs.add(getTestInput(dataset));
       });

       return inputs;
    }

    private TestInput getTestInput(String dataset) {
        String path = "/datasets/facebook/" + dataset + ".edges";

        return TestInput.builder()
                .dataset(dataset)
                .datasetCategory(StringUtils.extractCategory(dataset))
                .graph(GraphUtils.loadUnweightedGraph(path))
                .build();
    }
}
