package info.uaic.ro.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeError {
    private long line;
    private String message;

    public static CodeError of(long line, String message) {
        return new CodeError(line, message);
    }
}
