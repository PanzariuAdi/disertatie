package info.uaic.ro.sandbox.config;


import info.uaic.ro.sandbox.repositories.GraphRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final GraphRepository graphRepository;

    @PostConstruct
    public void initData() {

    }
}
