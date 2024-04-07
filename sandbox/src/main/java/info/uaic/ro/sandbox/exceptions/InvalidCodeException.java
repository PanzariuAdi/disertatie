package info.uaic.ro.sandbox.exceptions;

import lombok.Getter;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.List;

@Getter
public class InvalidCodeException extends Exception {

    private final List<Diagnostic<? extends JavaFileObject>> diagnostics;

    public InvalidCodeException(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        this.diagnostics = diagnostics;
    }
}
