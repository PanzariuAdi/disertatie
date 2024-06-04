package info.uaic.ro.backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test_cases")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "test_case_type", discriminatorType = DiscriminatorType.STRING)
public abstract class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "algorithm_type_id", nullable = false)
    private AlgorithmType algorithmType;

    private int caseNumber;
    private int duration;
    private int memory;
    private String dataset;
}
