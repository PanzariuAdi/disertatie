package info.uaic.ro.backend.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlgorithmRequestDto {

    AlgorithmDto algorithm;
    List<String> datasets;

}
