package info.uaic.ro.backend.mappers;

import info.uaic.ro.backend.models.dto.TestCaseDto;
import info.uaic.ro.backend.models.entities.TestCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "algorithmType", target = "algorithmType"),
            @Mapping(source = "duration", target = "duration"),
            @Mapping(source = "memory", target = "memory"),
            @Mapping(source = "dataset.fileName", target = "datasetFilename"),
            @Mapping(source = "expected", target = "expected"),
    })
    TestCaseDto toDto(TestCase testCase);
}
