package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.dto.DatasetDto;
import info.uaic.ro.backend.services.DatasetService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/datasets")
@RequiredArgsConstructor
public class DatasetController {

    private final DatasetService datasetService;

    @GetMapping("/all")
    public ResponseEntity<List<DatasetDto>> findAll() {
        return ResponseEntity.ok(datasetService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<DatasetDto>> findAllBy(@RequestParam String algorithmName) {
        return ResponseEntity.ok(datasetService.findByAlgorithm(algorithmName));
    }
}
