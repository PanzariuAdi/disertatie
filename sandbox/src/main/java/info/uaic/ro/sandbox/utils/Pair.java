package info.uaic.ro.sandbox.utils;

import java.util.Objects;

public record Pair<F, S>(F first, S second) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair<?, ?> p)) {
            return false;
        }
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }
}
