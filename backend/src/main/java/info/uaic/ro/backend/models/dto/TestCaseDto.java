package info.uaic.ro.backend.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TestCaseDto {

    private UUID id;
    private AlgorithmTypeDto algorithmType;
    private long duration;
    private long memory;
    private String datasetFilename;
    private String expected;

}
