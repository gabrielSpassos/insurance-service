package com.gabrielspassos.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public enum InsuranceAnalysisEnum {

    ECONOMIC("economic", null, 0L),
    REGULAR("regular", 1L, 2L),
    RESPONSIBLE("responsible", 3L, null),
    INELIGIBLE("ineligible", null, null);

    private final String status;
    private final Long minRiskPoint;
    private final Long maxRiskPoint;

    public static String toStatus(InsuranceAnalysisEnum insuranceAnalysis) {
        if (isNull(insuranceAnalysis)) return null;

        return insuranceAnalysis.getStatus();
    }

    public static InsuranceAnalysisEnum getInsuranceAnalysisByRiskPoint(Long riskPoint) {
        return Stream.of(InsuranceAnalysisEnum.values())
                .filter(isAtMinRiskRange(riskPoint).and(isAtMaxRiskRange(riskPoint)))
                .findFirst()
                .orElse(INELIGIBLE);
    }

    private static Predicate<InsuranceAnalysisEnum> isAtMinRiskRange(Long riskPoint) {
        return insuranceAnalysisEnum -> {
            if (isNull(insuranceAnalysisEnum.getMinRiskPoint())) return true;

            return insuranceAnalysisEnum.getMinRiskPoint() <= riskPoint;
        };
    }

    private static Predicate<InsuranceAnalysisEnum> isAtMaxRiskRange(Long riskPoint) {
        return insuranceAnalysisEnum -> {
            if (isNull(insuranceAnalysisEnum.getMaxRiskPoint())) return true;

            return insuranceAnalysisEnum.getMaxRiskPoint() >= riskPoint;
        };
    }
}
