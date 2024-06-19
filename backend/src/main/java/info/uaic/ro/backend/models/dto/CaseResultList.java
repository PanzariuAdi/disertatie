package info.uaic.ro.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseResultList<T> {

    private T expected;
    private T actual;

    private long expectedDuration;
    private long expectedMemory;

    private long duration;
    private long memory;

    private boolean correct;
    private String dataset;

}
