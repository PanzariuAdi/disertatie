package info.uaic.ro.backend.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class SandboxResultDto<T> {

    List<SandboxCaseDto<T>> results;

}
