package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.dto.TestCaseDto;
import info.uaic.ro.backend.services.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test-cases")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping("/all")
    public ResponseEntity<List<TestCaseDto>> findAll() {
        return ResponseEntity.ok(testCaseService.findAll());
    }

}
