package info.uaic.ro.sandbox.controllers;

import info.uaic.ro.sandbox.algorithms.Centrality;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.models.TestInput;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CodeController {

    private final StatisticsService statisticsService;
    private final Centrality centrality;
    private final GraphRepository graphRepository;

    @PostMapping("/execute")
    public ResponseEntity<Statistics> receiveCode(@RequestParam String algorithmType, @RequestBody String code) {
        Statistics statistics = statisticsService.createStatistics(code);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/pl")
    public ResponseEntity<List<TestInput>> receiveCode(@RequestParam String algorithmType, @RequestParam boolean isRun) {
        return graphRepository.findAllByAlgorithmTypeAndIsRun(algorithmType, isRun);
    }
}
