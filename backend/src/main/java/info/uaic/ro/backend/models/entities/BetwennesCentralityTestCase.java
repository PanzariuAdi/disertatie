package info.uaic.ro.backend.models.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("BETWEENNESS_CENTRALITY")
public class BetwennesCentralityTestCase extends TestCase {

    @Lob
    private String expected;

    public void setExpected(Map<Integer, Double> expected) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.expected = objectMapper.writeValueAsString(expected);
    }

    public Map<Integer, Double> getExpected() throws JsonProcessingException {
        if (this.expected == null || this.expected.isEmpty()) {
            return new HashMap<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.expected, new TypeReference<HashMap<Integer, Double>>() {});
    }
}
