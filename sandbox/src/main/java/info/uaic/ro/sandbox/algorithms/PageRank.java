package info.uaic.ro.sandbox.algorithms;

import org.graph4j.Graph;

import java.util.HashMap;
import java.util.Map;

public class PageRank {

    public static double DAMPING_FACTOR = 0.85;
    public static double TOLERANCE = 0.0001;
    public static int MAX_ITERATIONS = 100;

    public static Map<Integer, Double> pageRank(Graph<?, ?> graph) {
       Map<Integer, Double> ranks = new HashMap<>();
       int totalNodes = graph.numVertices();

        for (int vertex : graph.vertices()) {
            ranks.put(vertex, 1.0 / totalNodes);
        }

       for (int i = 0; i < MAX_ITERATIONS; i++) {
          Map<Integer, Double> newRanks = new HashMap<>();

           for (int vertex : graph.vertices()) {
               newRanks.put(vertex, (1 - DAMPING_FACTOR) / totalNodes);
           }

          for (int vertex : graph.vertices()) {
              int[] neighbors = graph.neighbors(vertex);
              double rankContribution = ranks.get(vertex) / neighbors.length;
              for (int neighbor : neighbors) {
                  newRanks.put(neighbor, newRanks.get(neighbor) + DAMPING_FACTOR * rankContribution);
              }
          }

          double maxDiff = 0.0;
          for (int vertex : graph.vertices()) {
              double diff = Math.max(maxDiff, newRanks.get(vertex) - ranks.get(vertex));
              if (diff > maxDiff) {
                  maxDiff = diff;
              }

              if (maxDiff < TOLERANCE) {
                  break;
              }
          }
          ranks = newRanks;
       }

       return ranks;
    }

}
