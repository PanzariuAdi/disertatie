package info.uaic.ro.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeError {
    private long line;
    private String message;

    public static CodeError of(long line, String message) {
        return new CodeError(line, message);
    }
}
