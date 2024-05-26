package info.uaic.ro.sandbox.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Result implements Serializable {
    Object actual;
    int caseNumber;
    long duration;
    long memory;
}
