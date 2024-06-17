package info.uaic.ro.backend.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmTypeDto {

    private String name;
    private String signature;

}
