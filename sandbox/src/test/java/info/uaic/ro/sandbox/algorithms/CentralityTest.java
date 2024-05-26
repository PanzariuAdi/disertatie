package info.uaic.ro.sandbox.algorithms;

import info.uaic.ro.sandbox.repositories.GraphRepository;
import org.graph4j.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CentralityTest {
//
//    Centrality centrality;
//    GraphRepository graphRepository;
//
//    @Before
//    public void setup() {
//        centrality = new Centrality();
//        graphRepository = new GraphRepository();
//    }
//
//    @Test
//    public void testBetweennessCentrality() {
//        Graph<?, ?> inputGraph = graphRepository.getFacebookTestInput("69").getGraph();
//
//        Map<Integer, Double> expected = getExpectedBetweennessMap();
//        Map<Integer, Double> actual = centrality.calculateBetweennessCentrality(inputGraph);
//
//        assertEquals(expected, actual);
//    }
//
//    private Map<Integer, Double> getExpectedBetweennessMap() {
//        Map<Integer, Double> expected = new HashMap<>();
//
//        expected.put(0, 0d);
//        expected.put(1, 0d);
//        expected.put(2, 3.0);
//        expected.put(3, 3.0);
//        expected.put(4, 7.0);
//        expected.put(5, 0.0);
//
//        return expected;
//    }
}