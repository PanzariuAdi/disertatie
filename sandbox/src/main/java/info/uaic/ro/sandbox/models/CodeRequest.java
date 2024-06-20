package info.uaic.ro.sandbox.models;

import lombok.Data;

import java.util.List;

@Data
public class CodeRequest {

    String code;
    List<String> datasets;

}
