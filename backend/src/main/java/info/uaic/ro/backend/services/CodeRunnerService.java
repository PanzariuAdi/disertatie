package info.uaic.ro.backend.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CodeRunnerService {

    private final static String TEMP = "/home/adi/personale/disertatie/temp";
    private final static String SANDBOX_NAME= "Sandbox.java";

    public void runCode(String code) {
        createSourceFile(code);
    }

    private void createSourceFile(String sourceCode) {
        File directory = new File(TEMP);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
        }

        File sourceFile = new File(TEMP + File.separator + SANDBOX_NAME);
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(sourceCode);
            System.out.println("File was created: " + sourceFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error at writing in the file !");
            e.printStackTrace();
        }
    }
}
