package info.uaic.ro.backend.exceptions;

import info.uaic.ro.backend.models.dto.CodeErrorDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CodeErrorException extends Exception {
    private List<CodeErrorDto> codeErrorDtos;

    public CodeErrorException(List<CodeErrorDto> codeErrorDtos) {
        this.codeErrorDtos = codeErrorDtos;
    }
}
