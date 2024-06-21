package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.mappers.TestCaseMapper;
import info.uaic.ro.backend.models.dto.TestCaseDto;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import info.uaic.ro.backend.services.TestCaseService;
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
    private final TestCaseService testCaseService;

    @GetMapping("/all")
    public ResponseEntity<List<TestCaseDto>> findAll() {
        return ResponseEntity.ok(testCaseService.findAll());
    }

//    @GetMapping
//    public ResponseEntity<List<TestCaseDto>> findBy(@RequestParam String algorithm) {

//        return ResponseEntity.ok(testCaseService.findAllBy(algorithm));
//    }

}
