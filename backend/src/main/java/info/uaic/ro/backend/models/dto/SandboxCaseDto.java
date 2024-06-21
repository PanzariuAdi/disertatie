package info.uaic.ro.backend.models.dto;

import lombok.Data;

@Data
public class SandboxCaseDto<T> {

    private T actual;
    private long duration;
    private long memory;
    private String dataset;
    private String datasetCategory;

}
