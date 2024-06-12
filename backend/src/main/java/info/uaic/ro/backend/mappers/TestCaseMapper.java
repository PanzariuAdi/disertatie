package info.uaic.ro.backend.mappers;

import info.uaic.ro.backend.models.dto.TestCaseDto;
import info.uaic.ro.backend.models.entities.TestCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {

    TestCaseDto toDto(TestCase testCase);
    TestCase toEntity(TestCaseDto algorithmTypeDto);
}
