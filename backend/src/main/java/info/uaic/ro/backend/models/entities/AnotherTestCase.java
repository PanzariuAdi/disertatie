package info.uaic.ro.backend.models.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ANOTHER")
public class AnotherTestCase extends TestCase {

    @Lob
    private String expected;

    public AnotherTestCase() {
    }

    public void setExpected(List<Integer> expected) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.expected = objectMapper.writeValueAsString(expected);
    }

    public List<Integer> getExpected() throws JsonProcessingException {
        if (this.expected == null || this.expected.isEmpty()) {
            return new ArrayList<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.expected, new TypeReference<>() {});
    }
}
