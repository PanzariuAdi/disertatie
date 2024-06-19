package info.uaic.ro.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetDto {

    private long id;
    private String fileName;
    private String category;
    private boolean directed;
    private boolean weighted;

}
