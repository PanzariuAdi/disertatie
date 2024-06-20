package info.uaic.ro.backend.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlgorithmRequest {

    AlgorithmDto algorithm;
    List<String> datasets;

}
