package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.services.CodeRunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CodeRunnerController {

    private final CodeRunnerService codeRunnerService;

    @PostMapping("/run")
    public ResponseEntity<String> runCode(@RequestBody String code) {
        codeRunnerService.runCode(code);
        return ResponseEntity.ok("OKAY");
    }
}
