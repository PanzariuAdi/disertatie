package info.uaic.ro.backend.controllers;

import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/algorithms")
@RequiredArgsConstructor
public class AlgorithmTypeController {

    private final AlgorithmTypeRepository algorithmTypeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<AlgorithmType>> findAll() {
        return ResponseEntity.ok(algorithmTypeRepository.findAll());
    }

    @GetMapping("/all/{algorithmType}")
    public ResponseEntity<AlgorithmType> findByName(@PathVariable String algorithmType) {
        Optional<AlgorithmType> optional = algorithmTypeRepository.findByName(algorithmType);

        if (optional.isEmpty()) { // TODO refactor
            throw new IllegalArgumentException();
        }

        return ResponseEntity.ok(optional.get());
    }
}

