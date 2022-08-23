package com.gabrielspassos.builder;

import com.gabrielspassos.dto.InsuranceAnalysisDTO;

public class InsuranceAnalysisDTOBuilder {
    private static final Integer INITIAL_RISK_POINTS = 0;

    public static InsuranceAnalysisDTO buildInitialAnalysis() {
        return InsuranceAnalysisDTO.builder()
                .autoRiskPoints(INITIAL_RISK_POINTS)
                .disabilityRiskPoints(INITIAL_RISK_POINTS)
                .homeRiskPoints(INITIAL_RISK_POINTS)
                .lifeRiskPoints(INITIAL_RISK_POINTS)
                .build();
    }
}
