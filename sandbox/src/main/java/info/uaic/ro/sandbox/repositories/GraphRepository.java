package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.utils.GraphUtils;
import org.graph4j.Graph;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GraphRepository {

    private static final String FACEBOOK_EGO_PATH = "/home/adi/personale/disertatie/datasets/ego/facebook/";
    private static final String EDGES_EXTENSION = ".edges";
    private static final List<String> datasets = List.of("0", "107", "348", "414", "686", "698", "1684", "1912", "3437", "3980");

    public List<Graph<?, ?>> getFacebookGraphs() {
        List<Graph<?, ?>> graphs = new ArrayList<>();
        datasets.forEach(ds -> graphs.add(GraphUtils.createGraphFromPath(FACEBOOK_EGO_PATH + ds + EDGES_EXTENSION)));
        return graphs;
    }
}
