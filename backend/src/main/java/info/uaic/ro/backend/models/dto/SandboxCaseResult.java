package info.uaic.ro.backend.models.dto;

import lombok.Data;

@Data
public class SandboxCaseResult<T> {
    private int caseNumber;
    private long duration;
    private long memory;
    private T actual;
}
