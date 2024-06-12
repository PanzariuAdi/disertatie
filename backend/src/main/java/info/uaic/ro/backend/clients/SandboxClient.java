package info.uaic.ro.backend.clients;

import info.uaic.ro.backend.models.dto.SandboxResultDto;

public interface SandboxClient {

    SandboxResultDto<?> getResultFor(String code, String datasetCategory);
    SandboxResultDto<?> getResultFor(String code);

}
