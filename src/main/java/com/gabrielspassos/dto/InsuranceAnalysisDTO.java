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
    private Integer autoRiskPoints;
    private InsuranceAnalysisEnum disability;
    private Integer disabilityRiskPoints;
    private InsuranceAnalysisEnum home;
    private Integer homeRiskPoints;
    private InsuranceAnalysisEnum life;
    private Integer lifeRiskPoints;

    public static Integer calculateRiskPoints(Integer currentRiskPoints, Integer pointsToCalculate) {
        return Integer.sum(currentRiskPoints, pointsToCalculate);
    }

    public void updateAllRiskPoints(Integer riskPointsToCalculate) {
        this.setAutoRiskPoints(calculateRiskPoints(this.getAutoRiskPoints(), riskPointsToCalculate));
        this.setDisabilityRiskPoints(calculateRiskPoints(this.getDisabilityRiskPoints(), riskPointsToCalculate));
        this.setHomeRiskPoints(calculateRiskPoints(this.getHomeRiskPoints(), riskPointsToCalculate));
        this.setLifeRiskPoints(calculateRiskPoints(this.getLifeRiskPoints(), riskPointsToCalculate));
    }

}
