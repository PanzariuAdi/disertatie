package info.uaic.ro.sandbox.compiler;

import lombok.Getter;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.util.Hashtable;
import java.util.Map;

@Getter
public class InMemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private final Map<String, JavaClassAsBytes> compiledClasses;
    private final ClassLoader loader;

    public InMemoryFileManager(JavaFileManager fileManager) {
        super(fileManager);
        this.compiledClasses = new Hashtable<>();
        this.loader = new InMemoryClassLoader(this.getClass().getClassLoader(), this);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        JavaClassAsBytes classAsBytes = new JavaClassAsBytes(className, kind);
        compiledClasses.put(className, classAsBytes);
        return classAsBytes;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return loader;
    }
}
