package info.uaic.ro.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test_cases")
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "algorithm_type_id", nullable = false)
    @JsonBackReference
    private AlgorithmType algorithmType;

    private long duration;
    private long memory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dataset_id", nullable = false)
    @JsonBackReference
    private Dataset dataset;

    @Lob
    private String expected;
}
