package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.exceptions.InvalidCodeException;
import info.uaic.ro.sandbox.compiler.InMemoryFileManager;
import info.uaic.ro.sandbox.compiler.JavaSourceFromString;
import lombok.SneakyThrows;
import org.graph4j.Graph;
import org.springframework.stereotype.Service;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Service
public class DynamicCodeExecutorService {

    private static final String CLASS_NAME = "Solution";
    private static final String METHOD_NAME = "calculate";

    @SneakyThrows
    public void executeDynamicCode(String sourceCode, Graph<?, ?> graph) {
        ClassLoader classLoader;

        try (InMemoryFileManager manager = compileSourceCode(sourceCode)) {
            classLoader = manager.getClassLoader(null);

            Class<?> solutionClass = Class.forName(CLASS_NAME, true, classLoader);

            Object solutionObject = solutionClass.getDeclaredConstructor().newInstance();
            Method calculateMethod = solutionClass.getMethod(METHOD_NAME, Graph.class);

            calculateMethod.invoke(solutionObject, graph);
        }
    }

    private InMemoryFileManager compileSourceCode(String sourceCode) throws InvalidCodeException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(CLASS_NAME, sourceCode));
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, sourceFiles);

        if (!task.call()) {
            throw new InvalidCodeException(diagnostics.getDiagnostics());
        }

        return manager;
    }
}
