package info.uaic.ro.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private List<CaseResult<?>> caseResultList;
    private int totalCases;
}