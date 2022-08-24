package com.gabrielspassos.dto;

import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceAnalysisDTO {

    private InsuranceAnalysisEnum auto;
    private Long autoRiskPoints;
    private InsuranceAnalysisEnum disability;
    private Long disabilityRiskPoints;
    private InsuranceAnalysisEnum home;
    private Long homeRiskPoints;
    private InsuranceAnalysisEnum life;
    private Long lifeRiskPoints;

    public static Long calculateRiskPoints(Long currentRiskPoints, Long pointsToCalculate) {
        return Long.sum(currentRiskPoints, pointsToCalculate);
    }

    public void updateAllRiskPoints(Long riskPointsToCalculate) {
        this.setAutoRiskPoints(calculateRiskPoints(this.getAutoRiskPoints(), riskPointsToCalculate));
        this.setDisabilityRiskPoints(calculateRiskPoints(this.getDisabilityRiskPoints(), riskPointsToCalculate));
        this.setHomeRiskPoints(calculateRiskPoints(this.getHomeRiskPoints(), riskPointsToCalculate));
        this.setLifeRiskPoints(calculateRiskPoints(this.getLifeRiskPoints(), riskPointsToCalculate));
    }

}
