package info.uaic.ro.sandbox.controllers;

import info.uaic.ro.sandbox.services.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CodeController {

    private final GraphService graphService;

    @PostMapping("/execute")
    public ResponseEntity<String> receiveCode(@RequestBody String code) throws Exception {
        graphService.executeAlgorithm(code);

        return ResponseEntity.ok("OK");
    }
}
