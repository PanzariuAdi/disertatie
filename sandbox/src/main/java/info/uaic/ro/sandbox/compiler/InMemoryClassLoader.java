package info.uaic.ro.sandbox.compiler;

import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {

    private final InMemoryFileManager manager;

    public InMemoryClassLoader(ClassLoader parent, InMemoryFileManager manager)  {
        super(parent);
        this.manager = manager;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Map<String, JavaClassAsBytes> compiledClasses = manager.getCompiledClasses();

        if (compiledClasses.containsKey(name)) {
            byte[] bytes = compiledClasses.get(name).getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException();
        }
    }
}
