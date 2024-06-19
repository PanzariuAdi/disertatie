package info.uaic.ro.backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "algorithm_types")
public class AlgorithmType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String signature;

    @ManyToMany(mappedBy = "algorithms")
    private List<Dataset> datasets;

    @Override
    public String toString() {
        return "AlgorithmType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlgorithmType that = (AlgorithmType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, signature);
    }

}
