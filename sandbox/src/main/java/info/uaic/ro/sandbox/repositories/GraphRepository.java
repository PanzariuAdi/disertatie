package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.utils.GraphUtils;
import info.uaic.ro.sandbox.utils.StringUtils;
import org.graph4j.Graph;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphRepository {

    private final List<String> datasets;
    private final int UNDIRECTED = 9;
    private final int DIRECTED  = 5;
    private final int WEIGHTED = 5;

    public GraphRepository() {
        datasets = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i < UNDIRECTED) datasets.add("undirected" + i);
            if (i < DIRECTED) datasets.add("directed" + i);
            if (i < WEIGHTED) datasets.add("weighted" + i);
        }
    }

    public TestInput getInputFor(String dataset) {
        Graph<Object, Integer> graph;
        String path = "/datasets/" + dataset + ".edges";
        String category = StringUtils.extractCategory(dataset);

        if (category.startsWith("weighted")) {
            graph = GraphUtils.loadWeightedGraph(path);

        } else {
            graph = GraphUtils.loadUnweightedGraph(path);
        }

        return TestInput.builder()
                .dataset(dataset)
                .datasetCategory(category)
                .graph(graph)
                .build();
    }
}
