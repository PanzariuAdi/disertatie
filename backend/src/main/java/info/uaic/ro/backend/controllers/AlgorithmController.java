package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.dto.AlgorithmRequest;
import info.uaic.ro.backend.models.dto.AlgorithmTypeDto;
import info.uaic.ro.backend.services.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/algorithms")
@RequiredArgsConstructor
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    @GetMapping("/all")
    public ResponseEntity<List<AlgorithmTypeDto>> findAll() {
        return ResponseEntity.ok(algorithmService.findAll());
    }

    @GetMapping("/all/{algorithmType}")
    public ResponseEntity<AlgorithmTypeDto> findByName(@PathVariable String algorithmType) {
        return ResponseEntity.ok(algorithmService.findByName(algorithmType));
    }

    @PostMapping
    public ResponseEntity<String> addAlgorithm(@RequestBody AlgorithmRequest request) {
        algorithmService.addAlgorithm(request);
        return ResponseEntity.ok("OK");
    }
}

