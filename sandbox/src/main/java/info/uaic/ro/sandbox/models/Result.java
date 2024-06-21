package info.uaic.ro.sandbox.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private Object actual;
    private long duration;
    private long memory;
    private String dataset;
    private String datasetCategory;

}
