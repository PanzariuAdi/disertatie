package info.uaic.ro.sandbox.exceptions;

import info.uaic.ro.sandbox.models.CodeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<List<CodeError>> invalidClassName(ClassNotFoundException e) {
        List<CodeError> errors = Collections.singletonList(CodeError.of(0, "Class name MUST be Solution!"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<List<CodeError>> invalidFunctionName(NoSuchMethodException e) {
        List<CodeError> errors = Collections.singletonList(CodeError.of(0, "The name of the function is incorrect!"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<List<CodeError>> missingPublicIdentifier(IllegalAccessException e) {
        List<CodeError> errors = Collections.singletonList(CodeError.of(0, "The class and the method must be public!\n"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(IncorrectReturnTypeException.class)
    public ResponseEntity<List<CodeError>> incorrectReturnType(IncorrectReturnTypeException e) {
        List<CodeError> errors = Collections.singletonList(CodeError.of(0, "Incorrect return type!\n"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
