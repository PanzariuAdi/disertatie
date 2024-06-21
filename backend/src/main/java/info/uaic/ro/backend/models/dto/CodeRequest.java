package info.uaic.ro.backend.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class CodeRequest {

    String code;
    List<String> datasets;

}
