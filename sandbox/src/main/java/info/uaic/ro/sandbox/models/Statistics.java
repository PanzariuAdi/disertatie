package info.uaic.ro.sandbox.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Statistics implements Serializable {
    private final List<Result> results;

    public Statistics() {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        results.add(result);
    }
}
