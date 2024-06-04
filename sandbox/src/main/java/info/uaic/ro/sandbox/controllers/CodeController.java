package info.uaic.ro.sandbox.controllers;

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
    public ResponseEntity<Statistics> receiveCode(@RequestBody String code, @RequestParam String dataset) {
        Statistics statistics = statisticsService.createStatistics(code, dataset);
        return ResponseEntity.ok(statistics);
    }

}
