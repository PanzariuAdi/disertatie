package info.uaic.ro.backend.mappers;

import info.uaic.ro.backend.models.dto.AlgorithmTypeDto;
import info.uaic.ro.backend.models.entities.AlgorithmType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlgorithmMapper {

    AlgorithmTypeDto toDto(AlgorithmType algorithmType);
    AlgorithmType toEntity(AlgorithmTypeDto algorithmTypeDto);

}
