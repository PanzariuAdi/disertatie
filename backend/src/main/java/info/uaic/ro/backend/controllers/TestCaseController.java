package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.services.TestCaseService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/test-cases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    @Cacheable
    @GetMapping("/all")
    public List<TestCase> findAll() {
       return testCaseService.findAll();
    }

    @Cacheable
    @GetMapping("/all/{algorithmType}")
    public List<TestCase> findAllBy(@PathVariable String algorithmType) {
       return testCaseService.findAllByAlgorithmType(algorithmType);
    }

}
