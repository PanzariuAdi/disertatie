package info.uaic.ro.sandbox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<List<String>> invalidCodeHandler(InvalidCodeException e) {
        List<String> compilationErrors = new ArrayList<>();

        for (Diagnostic<?> diagnostic : e.getDiagnostics()) {
            compilationErrors.add(String.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic.getSource().toString()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(compilationErrors);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<String> invalidClassName(ClassNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Class name MUST be Solution!");
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<String> invalidFunctionName(NoSuchMethodException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name of the function is incorrect!");
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> missingPublicIdentifier(IllegalAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The class and the method must be public!");
    }
}
