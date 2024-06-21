package info.uaic.ro.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.uaic.ro.backend.clients.SandboxClient;
import info.uaic.ro.backend.exceptions.BackendException;
import info.uaic.ro.backend.mappers.AlgorithmMapper;
import info.uaic.ro.backend.models.dto.*;
import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.Dataset;
import info.uaic.ro.backend.models.entities.TestCase;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.DatasetRepository;
import info.uaic.ro.backend.repositories.TestCasesRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlgorithmService {

    private final AlgorithmMapper algorithmMapper;
    private final AlgorithmTypeRepository algorithmTypeRepository;
    private final DatasetRepository datasetRepository;
    private final SandboxClient sandboxClient;
    private final TestCasesRepository testCasesRepository;
    private final DatasetService datasetService;
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

    @Transactional
    public void addAlgorithm(AlgorithmRequest algorithmRequest) {
        AlgorithmType algorithmType = saveAlgorithmType(algorithmRequest.getAlgorithm());

        CodeRequest codeRequest = new CodeRequest();
        codeRequest.setCode(algorithmRequest.getAlgorithm().getCode());
        codeRequest.setDatasets(algorithmRequest.getDatasets());

        SandboxResultDto<?> result = sandboxClient.getResultFor(codeRequest);

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

            addDatasetToAlgorithm(algorithmType.getId(), sandboxCase.getDataset(), sandboxCase.getDatasetCategory());

            Dataset dataset = fetchDataset(sandboxCase.getDataset(), algorithmType);
            testCase.setDataset(dataset);
            testCasesRepository.save(testCase);
        });
    }

    public void addDatasetToAlgorithm(UUID algorithmUUID, String fileName, String category) {
        AlgorithmType algorithmType = algorithmTypeRepository.findById(algorithmUUID)
                .orElseThrow(() -> new RuntimeException("AlgorithmType not found"));

        Dataset dataset = new Dataset();
        dataset.setFileName(fileName);
        dataset.setCategory(category);
        dataset.setAlgorithms(Collections.singletonList(algorithmType));

        datasetRepository.save(dataset);
    }

    @SneakyThrows
    private AlgorithmType saveAlgorithmType(AlgorithmDto algorithmDto) {
        AlgorithmTypeDto algorithmTypeDto = new AlgorithmTypeDto(algorithmDto.getName(), algorithmDto.getSignature());

        AlgorithmType algorithmType = algorithmMapper.toEntity(algorithmTypeDto);

        Optional<AlgorithmType> optional = algorithmTypeRepository.findByName(algorithmType.getName());

        if (optional.isPresent()) {
            throw new BackendException(Collections.singletonList(new ErrorDto("Algorithm already exists in the db!")));
        }

        return algorithmTypeRepository.save(algorithmType);
    }

    private Dataset fetchDataset(String fileName, AlgorithmType algorithmType) {
        return datasetRepository.findByFileNameAndAlgorithms(fileName, algorithmType)
                .orElseThrow(() -> new RuntimeException("Dataset not found: " + fileName));
    }

}
