package info.uaic.ro.sandbox.models;

import lombok.Builder;
import lombok.Data;
import org.graph4j.Graph;

@Data
@Builder
public class TestInput {

    private String dataset;
    private String datasetCategory;
    private Graph<?, ?> graph;

}
