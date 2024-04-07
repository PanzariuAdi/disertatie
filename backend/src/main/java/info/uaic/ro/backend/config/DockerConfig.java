package info.uaic.ro.backend.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class DockerConfig {

    private final static String DOCKER_HOST = "unix:///var/run/docker.sock";

    @Bean
    public DockerClient dockerClient() {
        DefaultDockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withApiVersion("1.40")
                .withDockerHost(DOCKER_HOST)
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(URI.create(DOCKER_HOST))
                .build();

        return DockerClientImpl.getInstance(clientConfig, httpClient);
    }
}
