package info.uaic.ro.sandbox.models;

import lombok.Data;

@Data
public class AlgorithmDto {

    private String name;
    private String signature;
    private String code;
    private boolean weighted;
    private boolean unweighted;

}
