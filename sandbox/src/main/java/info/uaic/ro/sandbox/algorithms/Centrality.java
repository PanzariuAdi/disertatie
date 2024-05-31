package info.uaic.ro.sandbox.algorithms;

import org.graph4j.Graph;

import java.util.*;

public class Centrality {

    private static final int MAX_ITERATIONS_DEFAULT = 100;
    private static final double TOLERANCE_DEFAULT = 0.001;
    private static final double DAMPING_FACTOR_DEFAULT = 0.01d;

    public Map<Integer, Double> calculateKatzCentrality(Graph<?, ?> graph) {
        Map<Integer, Double> katz = new HashMap<>();
        Map<Integer, Double> nextKatz = new HashMap<>();
        double tolerance = TOLERANCE_DEFAULT;
        int maxIterations = MAX_ITERATIONS_DEFAULT;
        double maxChange = tolerance;

        for (int vertex : graph.vertices()) {
            katz.put(vertex, 1.0);
        }

        while (maxIterations > 0 && maxChange >= tolerance) {
            maxChange = 0d;
            for (int v : graph.vertices()) {
                double contribution = 0d;

                for (int w : graph.neighbors(v)) {
                    contribution += DAMPING_FACTOR_DEFAULT * katz.get(w) * graph.getEdgeWeight(v, w);
                }

                double vOldValue = katz.get(v);
                double vNewValue = contribution + 1;
                maxChange = Math.max(maxChange, Math.abs(vNewValue - vOldValue));
                nextKatz.put(v, vNewValue);
            }

            Map<Integer, Double>  tmp = katz;
            katz = nextKatz;
            nextKatz = tmp;

            maxIterations--;
        }

        return katz;
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

            int n = graph.vertices().length - 1;
            int factor = (n - 1) * (n - 2);
            if (factor != 0) {
                Cb.forEach((v, score) -> Cb.put(v, score / factor));
            }

        }

        return Cb;
    }
}
