package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.mappers.TestCaseMapper;
import info.uaic.ro.backend.models.dto.TestCaseDto;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test-cases")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCasesRepository testCasesRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;
    private final TestCaseMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<TestCaseDto>> findAll() {
        return ResponseEntity.ok(
                testCasesRepository.findAll()
                        .stream()
                        .map(mapper::toDto)
                        .toList()
        );
    }

    @GetMapping
    public ResponseEntity<List<TestCase>> findBy(@RequestParam String algorithm) {
        List<TestCase> testCases = new ArrayList<>();
        algorithmTypeRepository.findByName(algorithm)
                .ifPresent(algorithmType -> {
                    testCases.addAll(testCasesRepository.findAllByAlgorithmType(algorithmType));
                });

        return ResponseEntity.ok(testCases);
    }

}
