package info.uaic.ro.sandbox.utils;

import lombok.experimental.UtilityClass;
import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@UtilityClass
public class GraphUtils {

    public static Graph<Object, Integer> loadGraph(String path, boolean isWeighted) {
        int maxVertex = getMaxVertex(path);
        Graph graph = GraphBuilder
                .vertexRange(0, maxVertex)
                .buildGraph();

        try (InputStream inputStream = GraphUtils.class.getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            int batchSize = 1000;
            Edge<Integer>[] edgeBatch = new Edge[batchSize];
            int batchIndex = 0;

            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");

                int source = Integer.parseInt(splitLine[0]);
                int destination = Integer.parseInt(splitLine[1]);
                double weight = isWeighted ? Double.parseDouble(splitLine[2]) : 1.0;

                maxVertex = Math.max(maxVertex, Math.max(source, destination));
                edgeBatch[batchIndex++] = isWeighted ? new Edge<>(source, destination, weight) : new Edge<>(source, destination);

                if (batchIndex >= batchSize) {
                    processEdgeBatch(graph, edgeBatch, batchIndex);
                    batchIndex = 0;
                }
            }
            if (batchIndex > 0) {
                processEdgeBatch(graph, edgeBatch, batchIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private static void processEdgeBatch(Graph<Object, Integer> graph, Edge<Integer>[] edgeBatch, int size) {
        for (int i = 0; i < size; i++) {
            Edge<Integer> edge = edgeBatch[i];
            int source = edge.source();
            int destination = edge.target();

            if (!graph.containsVertex(source)) graph.addVertex(source);
            if (!graph.containsVertex(destination)) graph.addVertex(destination);
            graph.addEdge(edge);
        }
    }

    private static int getMaxVertex(String path) {
        int maxVertex = 0;

        try (InputStream inputStream = GraphUtils.class.getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");
                int source = Integer.parseInt(splitLine[0]);
                int destination = Integer.parseInt(splitLine[1]);

                maxVertex = Math.max(maxVertex, Math.max(source, destination));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return maxVertex;
    }

    public static Graph<Object, Integer> loadUnweightedGraph(String path) {
        return loadGraph(path, false);
    }

    public static Graph<Object, Integer> loadWeightedGraph(String path) {
        return loadGraph(path, true);
    }

}
