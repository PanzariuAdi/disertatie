package info.uaic.ro.backend.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CaseResult<T> extends SandboxCaseResult<T> {
    private T expected;
    private int expectedDuration;
    private int expectedMemory;
    private boolean correct;
}
