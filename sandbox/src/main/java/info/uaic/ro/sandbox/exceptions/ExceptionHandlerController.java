package info.uaic.ro.sandbox.exceptions;

import info.uaic.ro.sandbox.models.CodeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<List<CodeError>> invalidCodeHandler(InvalidCodeException e) {
        List<CodeError> errors = new ArrayList<>();

        for (Diagnostic<?> diagnostic : e.getDiagnostics()) {
            if (diagnostic.getLineNumber() != -1) {
                errors.add(CodeError.of(diagnostic.getLineNumber(), diagnostic.getMessage(Locale.ENGLISH)));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<String> invalidClassName(ClassNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Class name MUST be Solution!\n" + e.getMessage());
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<String> invalidFunctionName(NoSuchMethodException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name of the function is incorrect!\n" + e.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> missingPublicIdentifier(IllegalAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The class and the method must be public!\n" + e.getMessage());
    }

    @ExceptionHandler(IncorrectReturnTypeException.class)
    public ResponseEntity<String> incorrectReturnType(IncorrectReturnTypeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
