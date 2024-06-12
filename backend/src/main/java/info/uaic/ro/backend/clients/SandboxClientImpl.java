package info.uaic.ro.backend.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.uaic.ro.backend.exceptions.CodeErrorException;
import info.uaic.ro.backend.models.dto.AlgorithmDto;
import info.uaic.ro.backend.models.dto.CodeErrorDto;
import info.uaic.ro.backend.models.dto.SandboxResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class SandboxClientImpl implements SandboxClient {

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${service.location.sandbox}")
    private String sandboxUrl;

    private final RestTemplate restTemplate;
    private static final String DATASET = "dataset";
    private static final String EXECUTE_ENDPOINT = "/execute";
    private static final String ALGORITHM_ENDPOINT = "/algorithms";

    public SandboxClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SandboxResultDto<?> getResultFor(String code, String dataset) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> requestEntity = new HttpEntity<>(code, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(sandboxUrl + EXECUTE_ENDPOINT)
                .queryParam(DATASET, dataset)
                .toUriString();

        try {
            return restTemplate.postForObject(url, requestEntity, SandboxResultDto.class);
        } catch (RestClientResponseException e) {
            return handleErrorResponse(e);
        }
    }

    @Override
    public SandboxResultDto<?> getResultFor(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> requestEntity = new HttpEntity<>(code, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(sandboxUrl + ALGORITHM_ENDPOINT).toUriString();

        try {
            return restTemplate.postForObject(url, requestEntity, SandboxResultDto.class);
        } catch (RestClientResponseException e) {
            return handleErrorResponse(e);
        }
    }

    private SandboxResultDto<?> handleErrorResponse(RestClientResponseException e) {
        try {
            String responseBody = e.getResponseBodyAsString();
            List<CodeErrorDto> errors = objectMapper.readValue(responseBody, new TypeReference<>(){});
            throw new CodeErrorException(errors);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse error response", ex);
        }
    }
}
