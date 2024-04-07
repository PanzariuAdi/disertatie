package info.uaic.ro.sandbox.services;

import info.uaic.ro.sandbox.repositories.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GraphService {

    private final GraphRepository graphRepository;
    private final DynamicCodeExecutorService dynamicCodeExecutorService;

    public void executeAlgorithm(String algorithm) {
       graphRepository.getFacebookGraphs().forEach(graph -> {
           dynamicCodeExecutorService.executeDynamicCode(algorithm, graph);
       });
    }

}
