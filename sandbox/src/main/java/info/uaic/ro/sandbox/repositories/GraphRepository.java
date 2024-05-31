package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.utils.GraphUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GraphRepository {

    private static final List<String> datasets = List.of("0", "107", "348", "414", "686", "698", "1684", "1912", "3437", "3980");
    private static final Map<String, List<TestInput>> runTestCases = new HashMap<>();
    private static final Map<String, List<TestInput>> submitTestCases = new HashMap<>();
    private static final String FACEBOOK_EGO_PATH = "/datasets/ego/facebook/";
    private static final String EDGES_EXTENSION = ".edges";

    static {
        List<TestInput> facebookInputs = new ArrayList<>();
        datasets.forEach(dataset -> facebookInputs.add(getFacebookTestInput(dataset)));

        submitTestCases.put("betweenness_centrality", facebookInputs);
        submitTestCases.put("katz_centrality", facebookInputs);

        runTestCases.put("betweenness_centrality", Collections.singletonList(getFacebookTestInput("69")));
        runTestCases.put("katz_centrality", Collections.singletonList(getFacebookTestInput("69")));
    }

    // load the graph in batches, maybe use an index or something
    public List<TestInput> getAllTestInputsAfter(String algorithmType, boolean isRun) {
        List<TestInput> list = isRun ? runTestCases.get(algorithmType) : submitTestCases.get(algorithmType);

        return list == null ? new ArrayList<>() : list;
    }

    private static TestInput getFacebookTestInput(String id) {
        return TestInput.builder()
                .id(id)
                .graph(GraphUtils.createGraphFromPath(FACEBOOK_EGO_PATH + id + EDGES_EXTENSION))
                .build();
    }
}
