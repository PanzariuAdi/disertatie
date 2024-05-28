package info.uaic.ro.sandbox.controllers;

import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.repositories.GraphRepository;
import info.uaic.ro.sandbox.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CodeController {

    private final StatisticsService statisticsService;
    private final GraphRepository graphRepository;

    @PostMapping("/execute")
    public ResponseEntity<Statistics> receiveCode(@RequestBody String code, @RequestParam String algorithmType, @RequestParam boolean isRun) {
        Statistics statistics = statisticsService.createStatistics(code, algorithmType, isRun);
        return ResponseEntity.ok(statistics);
    }

}
