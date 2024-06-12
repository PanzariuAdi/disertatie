package info.uaic.ro.backend.models.dto;

import info.uaic.ro.backend.models.entities.AlgorithmType;

public class TestCaseDto {

    private AlgorithmType algorithmType;
    private int duration;
    private int memory;
    private String dataset;
    private String datasetCategory;
    private String expected;
}
