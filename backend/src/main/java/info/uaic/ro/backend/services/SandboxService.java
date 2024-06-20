package info.uaic.ro.backend.services;

import info.uaic.ro.backend.clients.SandboxClient;
import info.uaic.ro.backend.models.dto.CodeRequest;
import info.uaic.ro.backend.models.dto.SandboxCaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SandboxService {

    private final SandboxClient sandboxClient;

    public Map<String, SandboxCaseDto<?>> getActualMap(CodeRequest codeRequest) {
        return sandboxClient.getResultFor(codeRequest)
                .getResults()
                .stream()
                .collect(Collectors.toMap(SandboxCaseDto::getDataset, sandboxCaseDto -> sandboxCaseDto));
    }

}
