package info.uaic.ro.sandbox.controllers;


import info.uaic.ro.sandbox.models.AlgorithmDto;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.services.AlgorithmService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/algorithms")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    @PostMapping()
    public ResponseEntity<Statistics> addAlgorithm(@RequestBody String code) {
        return ResponseEntity.ok(algorithmService.createStatisticsFor(code));
    }
}
