package info.uaic.ro.sandbox.algorithms;

import org.graph4j.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PageRank {
    public static final int MAX_ITERATIONS_DEFAULT = 100;
    public static final double TOLERANCE_DEFAULT = 0.0001;
    public static final double DAMPING_FACTOR_DEFAULT = 0.85d;
    private final Graph<?, ?> graph;

    private final double dampingFactor;
    private final int maxIterations;
    private final double tolerance;
    private Map<Integer, Double> scores;

    public PageRank(Graph<?, ?> graph, double dampingFactor, int maxIterations, double tolerance) {
        this.graph = graph;
        this.dampingFactor = dampingFactor;
        this.maxIterations = maxIterations;
        this.tolerance = tolerance;
    }

    private class Algorithm
    {
        private int totalVertices;
        private boolean isWeighted;

        private Map<Integer, Integer> vertexIndexMap;
        private Integer[] vertexMap;

        private double[] weightSum;
        private double[] curScore;
        private double[] nextScore;
        private int[] outDegree;
        private ArrayList<int[]> adjList;
        private ArrayList<double[]> weightsList;

        @SuppressWarnings("unchecked")
        public Algorithm()
        {
            this.totalVertices = graph.numVertices();
            this.isWeighted = graph.isEdgeWeighted();

            /*
             * Initialize score, map vertices to [0,n) and pre-compute degrees and adjacency lists
             */
            this.curScore = new double[totalVertices];
            this.nextScore = new double[totalVertices];
            this.vertexIndexMap = new HashMap<>();
//            this.vertexMap = (Integer[]) new Object[totalVertices];
            this.outDegree = new int[totalVertices];
            this.adjList = new ArrayList<>(totalVertices);

            double initScore = 1.0d / totalVertices;
            int i = 0;
            for (int v : graph.vertices()) {
                vertexIndexMap.put(v, i);
                vertexMap[i] = v;
                outDegree[i] = graph.degree(v);
                curScore[i] = initScore;
                i++;
            }

            for (i = 0; i < totalVertices; i++) {
                int v = vertexMap[i];
                int[] inNeighbors = new int[graph.inDegreeOf(v)];
                int j = 0;
                for (E e : graph.incomingEdgesOf(v)) {
                    V w = Graphs.getOppositeVertex(graph, e, v);
                    inNeighbors[j++] = vertexIndexMap.get(w);
                }
                adjList.add(inNeighbors);
            }
        }

        public Map<V, Double> getScores()
        {
            // compute
            if (isWeighted) {
                runWeighted();
            } else {
                run();
            }

            // make results user friendly
            Map<V, Double> scores = new HashMap<>();
            for (int i = 0; i < totalVertices; i++) {
                V v = vertexMap[i];
                scores.put(v, curScore[i]);
            }
            return scores;
        }

        private void run()
        {
            double maxChange = tolerance;
            int iterations = maxIterations;

            while (iterations > 0 && maxChange >= tolerance) {
                double r = teleProp();

                maxChange = 0d;
                for (int i = 0; i < totalVertices; i++) {
                    double contribution = 0d;
                    for (int w : adjList.get(i)) {
                        contribution += dampingFactor * curScore[w] / outDegree[w];
                    }

                    double vOldValue = curScore[i];
                    double vNewValue = r + contribution;
                    maxChange = Math.max(maxChange, Math.abs(vNewValue - vOldValue));
                    nextScore[i] = vNewValue;
                }

                // progress
                swapScores();
                iterations--;
            }
        }

        private double teleProp()
        {
            double r = 0d;
            for (int i = 0; i < totalVertices; i++) {
                if (outDegree[i] > 0) {
                    r += (1d - dampingFactor) * curScore[i];
                } else {
                    r += curScore[i];
                }
            }
            r /= totalVertices;
            return r;
        }

        private void swapScores()
        {
            double[] tmp = curScore;
            curScore = nextScore;
            nextScore = tmp;
        }

    }
}
