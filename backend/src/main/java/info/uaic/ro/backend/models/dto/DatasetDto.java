package info.uaic.ro.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetDto {

    private UUID id;
    private String fileName;
    private String category;
    private boolean directed;
    private boolean weighted;

}
