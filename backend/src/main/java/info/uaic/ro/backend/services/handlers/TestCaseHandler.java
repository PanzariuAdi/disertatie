package info.uaic.ro.backend.services.handlers;

import info.uaic.ro.backend.models.dto.CaseResult;
import info.uaic.ro.backend.models.dto.SandboxCaseResult;
import info.uaic.ro.backend.models.entities.TestCase;

public interface TestCaseHandler<T extends TestCase> {
    CaseResult<?> createCaseResult(T testCase, SandboxCaseResult<?> sandboxCaseResult);
}
