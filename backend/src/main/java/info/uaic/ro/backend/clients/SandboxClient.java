package info.uaic.ro.backend.clients;

import info.uaic.ro.backend.models.dto.CodeRequest;
import info.uaic.ro.backend.models.dto.SandboxResultDto;

public interface SandboxClient {

    SandboxResultDto<?> getResultFor(CodeRequest codeRequest);

}
