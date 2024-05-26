package info.uaic.ro.sandbox.algorithms;

import org.graph4j.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Centrality {

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
