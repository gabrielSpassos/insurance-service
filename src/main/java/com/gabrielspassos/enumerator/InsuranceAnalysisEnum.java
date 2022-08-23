package com.gabrielspassos.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public enum InsuranceAnalysisEnum {

    ECONOMIC("economic", null, 0),
    REGULAR("regular", 1, 2),
    RESPONSIBLE("responsible", 3, null),
    INELIGIBLE("ineligible", null, null);

    private final String status;
    private final Integer minRiskPoint;
    private final Integer maxRiskPoint;

    public static String toStatus(InsuranceAnalysisEnum insuranceAnalysis) {
        if (isNull(insuranceAnalysis)) return null;

        return insuranceAnalysis.getStatus();
    }

    public static InsuranceAnalysisEnum getInsuranceAnalysisByRiskPoint(Integer riskPoint) {
        return Stream.of(InsuranceAnalysisEnum.values())
                .filter(isAtMinRiskRange(riskPoint).and(isAtMaxRiskRange(riskPoint)))
                .findFirst()
                .orElse(INELIGIBLE);
    }

    private static Predicate<InsuranceAnalysisEnum> isAtMinRiskRange(Integer riskPoint) {
        return insuranceAnalysisEnum -> {
            if (isNull(insuranceAnalysisEnum.getMinRiskPoint())) return true;

            return insuranceAnalysisEnum.getMinRiskPoint() <= riskPoint;
        };
    }

    private static Predicate<InsuranceAnalysisEnum> isAtMaxRiskRange(Integer riskPoint) {
        return insuranceAnalysisEnum -> {
            if (isNull(insuranceAnalysisEnum.getMaxRiskPoint())) return true;

            return insuranceAnalysisEnum.getMaxRiskPoint() >= riskPoint;
        };
    }
}
