package info.uaic.ro.backend.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DockerService {

    private final DockerClient dockerClient;

    public void showAllContainers() {
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowAll(true)
                .exec();

        containers.forEach(System.out::println);
    }

}
