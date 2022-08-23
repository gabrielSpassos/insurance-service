package com.gabrielspassos.enumerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceAnalysisEnumTest {

    @Test
    void shouldReturnStatus() {
        assertEquals("economic", InsuranceAnalysisEnum.toStatus(InsuranceAnalysisEnum.ECONOMIC));
        assertEquals("regular", InsuranceAnalysisEnum.toStatus(InsuranceAnalysisEnum.REGULAR));
        assertEquals("responsible", InsuranceAnalysisEnum.toStatus(InsuranceAnalysisEnum.RESPONSIBLE));
        assertEquals("ineligible", InsuranceAnalysisEnum.toStatus(InsuranceAnalysisEnum.INELIGIBLE));
    }

    @Test
    void shouldReturnStatusAsNull() {
        assertNull(InsuranceAnalysisEnum.toStatus(null));
    }

    @Test
    void shouldReturnInsuranceAnalysisEconomicByRiskPoints() {
        assertEquals(InsuranceAnalysisEnum.ECONOMIC, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(-1));
        assertEquals(InsuranceAnalysisEnum.ECONOMIC, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(0));
    }

    @Test
    void shouldReturnInsuranceAnalysisRegularByRiskPoints() {
        assertEquals(InsuranceAnalysisEnum.REGULAR, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(1));
        assertEquals(InsuranceAnalysisEnum.REGULAR, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(2));
    }

    @Test
    void shouldReturnInsuranceAnalysisResponsibleByRiskPoints() {
        assertEquals(InsuranceAnalysisEnum.RESPONSIBLE, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(3));
        assertEquals(InsuranceAnalysisEnum.RESPONSIBLE, InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint(4));
    }
}