package info.uaic.ro.backend.exceptions;

import info.uaic.ro.backend.models.dto.ErrorDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BackendException extends Exception {

    private List<ErrorDto> errors;

    public BackendException(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
