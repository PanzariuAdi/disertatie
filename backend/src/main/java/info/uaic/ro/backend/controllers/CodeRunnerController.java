package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.dto.Statistics;
import info.uaic.ro.backend.services.ComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class CodeRunnerController {

    private final ComparisonService comparisonService;

    @PostMapping("/run")
    public ResponseEntity<Statistics> runCode(@RequestBody String code, @RequestParam String algorithmType, @RequestParam String dataset) {
        return ResponseEntity.ok(comparisonService.getStatistics(code, algorithmType, dataset));
    }
}
