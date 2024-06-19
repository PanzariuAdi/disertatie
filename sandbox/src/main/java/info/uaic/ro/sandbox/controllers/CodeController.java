package info.uaic.ro.sandbox.controllers;

import info.uaic.ro.sandbox.models.CodeRequest;
import info.uaic.ro.sandbox.models.Statistics;
import info.uaic.ro.sandbox.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
public class CodeController {

    private final StatisticsService statisticsService;

    @PostMapping("/execute")
    public ResponseEntity<Statistics> receiveCode(@RequestBody CodeRequest code) {
        Statistics statistics = statisticsService.createStatistics(code);
        return ResponseEntity.ok(statistics);
    }

}
