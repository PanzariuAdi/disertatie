package info.uaic.ro.backend.exceptions;

import info.uaic.ro.backend.models.dto.CodeErrorDto;
import info.uaic.ro.backend.models.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CodeErrorException.class)
    public ResponseEntity<List<CodeErrorDto>> invalidCode(CodeErrorException e) {
       return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<List<ErrorDto>> invalidCode(BackendException e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
