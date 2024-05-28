package info.uaic.ro.sandbox.algorithms;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.util.*;

public class Centrality {

    public static void main(String[] args) {
        Graph<?, ?> graph = GraphBuilder
                .numVertices(6)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .addEdge(4, 5)
                .buildGraph();

        double alpha = 0.1;
        double beta = 1.0;
        int maxIterations = 1000;
        double tol = 1e-6;

        double[] centrality = katzCentrality(graph, alpha, beta, maxIterations, tol);

        for (int i = 0; i < centrality.length; i++) {
            System.out.println("Katz Centrality of node " + i + ": " + centrality[i]);
        }
    }

    public static double[] katzCentrality(Graph<?, ?> graph, double alpha, double beta, int maxIterations, double tol) {
        int n = graph.numVertices();
        double[] centrality = new double[n];
        int[][] adjMatrix = graph.adjacencyMatrix();
        Arrays.fill(centrality, beta);

        double[] prevCentrality = new double[n];

        for (int iter = 0; iter < maxIterations; iter++) {
            System.arraycopy(centrality, 0, prevCentrality, 0, n);

            for (int i = 0; i < n; i++) {
                centrality[i] = beta;
                for (int j = 0; j < n; j++) {
                    centrality[i] += alpha * adjMatrix[i][j] * prevCentrality[j];
                }
            }

            double delta = 0.0;
            for (int i = 0; i < n; i++) {
                delta += Math.abs(centrality[i] - prevCentrality[i]);
            }
            if (delta < tol) {
                break;
            }
        }

        return centrality;
    }

    public Map<Integer, Double> calculateBetweennessCentrality(Graph<?, ?> graph) {
        Map<Integer, Double> Cb = new HashMap<>();

        for (int vertex : graph.vertices()) {
            Cb.put(vertex, 0.0);
        }

        for (int s : graph.vertices()) {
            Stack<Integer> S = new Stack<>();
            Map<Integer, List<Integer>> P = new HashMap<>();
            Map<Integer, Double> sigma = new HashMap<>();
            Map<Integer, Double> delta = new HashMap<>();
            LinkedList<Integer> Q = new LinkedList<>();

            for (int t : graph.vertices()) {
                P.put(t, new ArrayList<>());
                sigma.put(t, 0.0);
                delta.put(t, -1.0);
            }

            sigma.put(s, 1.0);
            delta.put(s, 0.0);
            Q.add(s);

            while(!Q.isEmpty()) {
                int v = Q.poll();
                S.push(v);
                for (int w : graph.neighbors(v)) {
                    if (delta.get(w) < 0) { // w found for the first time ?
                        Q.add(w);
                        delta.put(w, delta.get(v) + 1);
                    }

                    if (delta.get(w) == delta.get(v) + 1) { // shortest path to w via v ?
                        sigma.put(w, sigma.get(w) + sigma.get(v));
                        P.get(w).add(v);
                    }
                }
            }
            Map<Integer, Double> dependency = new HashMap<>();
            for (int v : graph.vertices()) dependency.put(v, 0.0);

            while(!S.isEmpty()) {
                int w = S.pop();

                for (int v : P.get(w)) {
                    double contribution = (sigma.get(v) / sigma.get(w)) * (1 + dependency.get(w));
                    dependency.put(v, dependency.get(v) + contribution);
                }

                if (w != s) {
                    Cb.put(w, Cb.get(w) + dependency.get(w));
                }
            }
        }

        return Cb;
    }
}
