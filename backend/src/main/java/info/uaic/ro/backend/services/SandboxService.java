package info.uaic.ro.backend.services;

import info.uaic.ro.backend.clients.SandboxClient;
import info.uaic.ro.backend.models.dto.SandboxResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SandboxService {

    private final SandboxClient sandboxClient;

    public SandboxResult<?> getSandboxResult(String code, String dataset) {
        SandboxResult<?> sandboxResult = sandboxClient.getResultFor(code, dataset);

        return sandboxResult;
    }
}
