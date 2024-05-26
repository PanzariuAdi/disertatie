package info.uaic.ro.sandbox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.graph4j.Graph;

@Data
@Entity
@Builder
@Table(name = "test_inputs")
public class TestInput {
    private String id;
    private boolean isRun;
    private Graph<?, ?> graph;
}
