package info.uaic.ro.sandbox.utils;

import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphUtils {

    public static Graph<Object, Integer> createGraphFromPath(String path) {
        List<Edge<String>> edges = new ArrayList<>();
        int maxVertex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");

                int source = Integer.parseInt(splitLine[0]);
                int destination = Integer.parseInt(splitLine[1]);

                maxVertex = Math.max(maxVertex, Math.max(source, destination));
                edges.add(new Edge<>(source, destination));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph graph = GraphBuilder
                .vertexRange(0, maxVertex)
                .buildGraph();

        graph.addVertices();

        for (Edge<String> edge : edges) {
            graph.addEdge(edge);
        }

        return graph;
    }

}
