package info.uaic.ro.sandbox.repositories;

import info.uaic.ro.sandbox.models.TestInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphRepository extends JpaRepository<TestInput, String> {

    List<TestInput> findAllByAlgorithmType(String algorithmType);
    List<TestInput> findAllByAlgorithmTypeAndIsRun(String algorithmType, boolean isRun);

//    private static final String FACEBOOK_EGO_PATH = "/datasets/ego/facebook/";
//    private static final String EDGES_EXTENSION = ".edges";
//    private static final List<String> datasets = List.of("0", "107", "348", "414", "686", "698", "1684", "1912", "3437", "3980");
//
//    // load the graph in batches, maybe use an index or something
//    public List<TestInput> getAllFacebookTestInputs(boolean isRun) {
//        List<TestInput> graphs = new ArrayList<>();
//        datasets.forEach(dataset -> {
//            graphs.add(
//                    TestInput.builder()
//                            .id(dataset)
//                            .graph(GraphUtils.createGraphFromPath(FACEBOOK_EGO_PATH + dataset + EDGES_EXTENSION))
//                            .build()
//            );
//        });
//        return graphs;
//    }
//
//    public TestInput getFacebookTestInput(String id) {
//        return TestInput.builder()
//                .id(id)
//                .graph(GraphUtils.createGraphFromPath(FACEBOOK_EGO_PATH + id + EDGES_EXTENSION))
//                .build();
//    }
}
