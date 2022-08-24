package com.gabrielspassos.builder;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import org.apache.commons.lang3.BooleanUtils;

public class InsuranceAnalysisDTOBuilder {

    public static InsuranceAnalysisDTO buildInitialAnalysis(CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO) {
        Long baseScore = createInsuranceAnalysisDTO.getRiskQuestions().stream().filter(BooleanUtils::isTrue).count();

        return InsuranceAnalysisDTO.builder()
                .autoRiskPoints(baseScore)
                .disabilityRiskPoints(baseScore)
                .homeRiskPoints(baseScore)
                .lifeRiskPoints(baseScore)
                .build();
    }
}
