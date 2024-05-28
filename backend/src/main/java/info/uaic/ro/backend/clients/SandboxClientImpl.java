package info.uaic.ro.backend.clients;

import info.uaic.ro.backend.models.dto.SandboxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SandboxClientImpl implements SandboxClient {

    private static final String ALGORITHM_TYPE_PARAM = "algorithmType";
    private static final String IS_RUN_PARAM = "isRun";
    private static final String EXECUTE_ENDPOINT = "/execute";
    private final RestTemplate restTemplate;

    @Value("${service.location.sandbox}")
    private String sandboxUrl;

    public SandboxClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SandboxResult<?> getResultFor(String code, String algorithmType, boolean isRun) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> requestEntity = new HttpEntity<>(code, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(sandboxUrl + EXECUTE_ENDPOINT)
                .queryParam(ALGORITHM_TYPE_PARAM, algorithmType)
                .queryParam(IS_RUN_PARAM, isRun)
                .toUriString();

        return restTemplate.postForObject(url, requestEntity, SandboxResult.class);
    }
}
