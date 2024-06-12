package info.uaic.ro.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.uaic.ro.backend.clients.SandboxClient;
import info.uaic.ro.backend.mappers.AlgorithmMapper;
import info.uaic.ro.backend.models.dto.AlgorithmDto;
import info.uaic.ro.backend.models.dto.AlgorithmTypeDto;
import info.uaic.ro.backend.models.dto.SandboxResultDto;
import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlgorithmService {

    private final AlgorithmMapper algorithmMapper;
    private final AlgorithmTypeRepository algorithmTypeRepository;
    private final SandboxClient sandboxClient;
    private final TestCasesRepository testCasesRepository;
    private final ObjectMapper objectMapper;

    public List<AlgorithmTypeDto> findAll() {
        return algorithmTypeRepository.findAll()
                .stream()
                .map(algorithmMapper::toDto)
                .toList();
    }

    public AlgorithmTypeDto findByName(String algorithmType) {
        return algorithmTypeRepository.findByName(algorithmType)
                .map(algorithmMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Algorithm type not found!"));
    }

    public void addAlgorithm(AlgorithmDto algorithmDto) {
        AlgorithmType algorithmType = saveAlgorithmType(algorithmDto);

        SandboxResultDto<?> result = sandboxClient.getResultFor(algorithmDto.getCode());

        result.getResults().forEach(sandboxCase -> {

            TestCase testCase = new TestCase();
            try {
                String expected = objectMapper.writeValueAsString(sandboxCase.getActual());
                testCase.setExpected(expected);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            testCase.setDuration(sandboxCase.getDuration());
            testCase.setMemory(sandboxCase.getMemory());
            testCase.setAlgorithmType(algorithmType);
            testCase.setDataset(sandboxCase.getDataset());
            testCase.setDatasetCategory(sandboxCase.getDatasetCategory());
            testCasesRepository.save(testCase);
        });

    }

    private AlgorithmType saveAlgorithmType(AlgorithmDto algorithmDto) {
        AlgorithmTypeDto algorithmTypeDto = new AlgorithmTypeDto(algorithmDto.getName(), algorithmDto.getSignature());

        AlgorithmType algorithmType = algorithmMapper.toEntity(algorithmTypeDto);
        return algorithmTypeRepository.save(algorithmType);
    }

}
