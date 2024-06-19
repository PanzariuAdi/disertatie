package info.uaic.ro.backend.services;

import info.uaic.ro.backend.mappers.DatasetMapper;
import info.uaic.ro.backend.models.dto.DatasetDto;
import info.uaic.ro.backend.models.entities.AlgorithmType;
import info.uaic.ro.backend.models.entities.Dataset;
import info.uaic.ro.backend.repositories.AlgorithmTypeRepository;
import info.uaic.ro.backend.repositories.DatasetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DatasetService {

    private final DatasetRepository datasetRepository;
    private final AlgorithmTypeRepository algorithmTypeRepository;
    private final DatasetMapper datasetMapper;

    public List<DatasetDto> findAll() {
        return datasetRepository.findAll()
                .stream()
                .map(datasetMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DatasetDto> findAllBy(boolean directed, boolean weighted) {
        return datasetRepository.findByDirectedAndWeighted(directed, weighted)
                .stream()
                .map(datasetMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DatasetDto> findByAlgorithm(String algorithm) {

        Optional<AlgorithmType> algorithmType = algorithmTypeRepository.findByName(algorithm);

        if (algorithmType.isEmpty()) {
            throw new IllegalArgumentException("yes");
        }

        List<Dataset> datasets = datasetRepository.findByAlgorithms(algorithmType.get());

        return datasets.stream().map(datasetMapper::toDto).toList();
    }
}
