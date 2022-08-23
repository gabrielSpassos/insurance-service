package com.gabrielspassos.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum InsuranceAnalysisEnum {

    ECONOMIC("economic"),
    REGULAR("regular"),
    RESPONSIBLE("responsible"),
    INELIGIBLE("ineligible");

    private final String status;

    public static String toStatus(InsuranceAnalysisEnum insuranceAnalysis) {
        if (Objects.isNull(insuranceAnalysis)) return null;

        return insuranceAnalysis.getStatus();
    }
}
