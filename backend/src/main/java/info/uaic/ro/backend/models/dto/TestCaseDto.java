package info.uaic.ro.backend.models.dto;

import lombok.Data;

@Data
public class TestCaseDto {

    private long id;
    private AlgorithmTypeDto algorithmType;
    private long duration;
    private long memory;
    private String datasetFilename;

}
