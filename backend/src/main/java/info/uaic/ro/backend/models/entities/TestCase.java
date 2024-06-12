package info.uaic.ro.backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test_cases")
@DiscriminatorColumn(name = "test_case_type", discriminatorType = DiscriminatorType.STRING)
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "algorithm_type_id", nullable = false)
    private AlgorithmType algorithmType;

    private long duration;
    private long memory;
    private String dataset;
    private String datasetCategory;

    @Lob
    private String expected;
}
