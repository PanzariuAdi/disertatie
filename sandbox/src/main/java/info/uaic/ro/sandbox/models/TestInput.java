package info.uaic.ro.sandbox.models;

import lombok.Builder;
import lombok.Data;
import org.graph4j.Graph;

@Data
@Builder
public class TestInput {
    private String id;
    private boolean isRun;
    private Graph<?, ?> graph;
}
