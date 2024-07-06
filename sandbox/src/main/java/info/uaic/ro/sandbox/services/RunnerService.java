package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.compiler.InMemoryFileManager;
import info.uaic.ro.sandbox.compiler.JavaSourceFromString;
import info.uaic.ro.sandbox.exceptions.InvalidCodeException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.graph4j.Graph;
import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class RunnerService {

    private static final String CLASS_NAME = "Solution";
    private static final String METHOD_NAME = "calculate";

    @SneakyThrows
    public Object runCode(String sourceCode, Graph<?, ?> graph) {
        try (InMemoryFileManager manager = compileSourceCode(sourceCode)) {
            ClassLoader classLoader = manager.getClassLoader(null);;

            Class<?> solutionClass = Class.forName(CLASS_NAME, true, classLoader);

            Object solutionObject = solutionClass.getDeclaredConstructor().newInstance();
            Method calculateMethod = solutionClass.getMethod(METHOD_NAME, Graph.class);

            return calculateMethod.invoke(solutionObject, graph);
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            log.error("Error executing code: {}", e.toString());
        }
        return null;
    }

    private InMemoryFileManager compileSourceCode(String sourceCode) throws InvalidCodeException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, Locale.ENGLISH, StandardCharsets.UTF_8));
        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(CLASS_NAME, sourceCode));

        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add("./graph4j-1.0.7.jar");

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sourceFiles);

        boolean success = task.call();

        if (success) {
            System.out.println("Compilation succeeded!");
        } else {
            System.out.println("Compilation failed!");
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                System.out.println(diagnostic.getMessage(null));
            }
            throw new InvalidCodeException(diagnostics.getDiagnostics());
        }
        return manager;
    }
}
