package info.uaic.ro.backend.services.handlers;

import info.uaic.ro.backend.models.entities.BetwennesCentralityTestCase;
import info.uaic.ro.backend.models.entities.KatzCentralityTestCase;
import info.uaic.ro.backend.models.entities.TestCase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestCaseHandlerRegistry {

    private static final Map<Class<? extends TestCase>, TestCaseHandler<? extends TestCase>> handlers = new HashMap<>();

    static {
        handlers.put(BetwennesCentralityTestCase.class, new BetweennessCentralityTestCaseHandler());
        handlers.put(KatzCentralityTestCase.class, new KatzCentralityTestCaseHandler());
    }

    @SuppressWarnings("unchecked")
    public static <T extends TestCase> TestCaseHandler<T> getHandler(Class<T> clazz) {
        return (TestCaseHandler<T>) handlers.get(clazz);
    }
}
