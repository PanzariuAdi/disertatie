package info.uaic.ro.sandbox.algorithms;

import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.utils.GraphUtils;
import org.graph4j.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralityLiveTest {

    private static final List<String> datasets = List.of("0", "107", "348", "414", "686", "698", "1684", "1912", "3437", "3980");
    private static final Map<String, List<TestInput>> runTestCases = new HashMap<>();
    private static final Map<String, List<TestInput>> submitTestCases = new HashMap<>();
    private static final String FACEBOOK_EGO_PATH = "/datasets/ego/facebook/";
    private static final String EDGES_EXTENSION = ".edges";

    public static void main(String[] args) {

        datasets.forEach(dataset -> {
            String fullPath = FACEBOOK_EGO_PATH + dataset + EDGES_EXTENSION;

            Graph<?, ?> graph = GraphUtils.createGraphFromPath(fullPath);

            Centrality centrality = new Centrality();
            Map<Integer, Double> map = centrality.calculateKatzCentrality(graph);

            System.out.println("------" + dataset + "------");
            System.out.print("{");
            map.forEach((k, v) -> System.out.print("\"" + k + "\": " + v + ", "));
            System.out.println("}");
            System.out.println("------" + dataset + "------");
        });
    }

}
