package info.uaic.ro.backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "datasets")
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean directed;
    private boolean weighted;

    private String fileName;
    private String category;

    @ManyToMany
    @JoinTable(
            name = "dataset_algorithm_type", // Name of the join table
            joinColumns = @JoinColumn(name = "dataset_id"), // Column name for dataset_id in join table
            inverseJoinColumns = @JoinColumn(name = "algorithm_type_id") // Column name for algorithm_type_id in join table
    )
    private List<AlgorithmType> algorithms;

    @Override
    public String toString() {
        return "Dataset{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dataset dataset = (Dataset) o;
        return id == dataset.id &&
                Objects.equals(fileName, dataset.fileName) &&
                Objects.equals(category, dataset.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, category);
    }
}
