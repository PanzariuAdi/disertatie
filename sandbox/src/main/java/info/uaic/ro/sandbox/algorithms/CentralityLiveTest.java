package info.uaic.ro.sandbox.algorithms;

import info.uaic.ro.sandbox.utils.GraphUtils;
import info.uaic.ro.sandbox.utils.MeasureUtils;
import org.graph4j.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralityLiveTest {

    private static final List<String> SMALL_DATASETS = List.of("0s, 1s, 2s, 3s, 4s");
    private static final List<String> MID_DATASETS = List.of("0m", "1m", "2m", "3m", "4m", "5m", "6m", "7m", "8m", "9m");
    private static final List<String> LARGE_DATASETS = List.of("tv_show", "politicians", "government", "public_figures", "athletes", "company", "new_sites", "artists");

    private static final String DATASETS_FACEBOOK = "/datasets/facebook/";

    public static void main(String[] args) {

        String dataset = "4s";
        Map<Integer, Double> map = new HashMap<>();
        double durationAvg = 0, memoryAvg = 0;

        System.gc();

        String fullPath = DATASETS_FACEBOOK + dataset + ".edges";

        Graph<?, ?> graph = GraphUtils.createGraphFromPath(fullPath);

        Centrality centrality = new Centrality();

        long start = System.currentTimeMillis();
        map = centrality.calculate(graph);
        long duration = System.currentTimeMillis() - start;
        long memoryUsed = MeasureUtils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        System.out.println("------" + dataset + "------");
        System.out.print("{");
        map.forEach((k, v) -> System.out.print("\"" + k + "\": " + v + ", "));
        System.out.println("}");
        System.out.println("duration: " + duration);
        System.out.println("memory: " + memoryUsed);
        System.out.println("------" + dataset + "------");
    }

}
