package info.uaic.ro.backend.clients;

import info.uaic.ro.backend.models.dto.SandboxResult;

public interface SandboxClient {

    SandboxResult<?> getResultFor(String code, String algorithmType);

}
