package info.uaic.ro.backend.exceptions;

import info.uaic.ro.backend.models.dto.CodeError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CodeErrorException extends Exception {
    private List<CodeError> codeErrors;

    public CodeErrorException(List<CodeError> codeErrors) {
        this.codeErrors = codeErrors;
    }
}
