package info.uaic.ro.backend.mappers;

import info.uaic.ro.backend.models.dto.DatasetDto;
import info.uaic.ro.backend.models.entities.Dataset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DatasetMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fileName", target = "fileName"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "directed", target = "directed"),
            @Mapping(source = "weighted", target = "weighted"),
    })
    DatasetDto toDto(Dataset dataset);

    Dataset toEntity(DatasetDto datasetDto);

}
