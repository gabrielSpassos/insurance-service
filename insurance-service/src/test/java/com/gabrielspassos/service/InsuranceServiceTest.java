package com.gabrielspassos.service;

import com.gabrielspassos.config.RiskAnalysisConfig;
import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import com.gabrielspassos.stub.CreateInsuranceAnalysisDTOStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InsuranceServiceTest {

    @InjectMocks
    private InsuranceService insuranceService;

    @Mock
    private RiskAnalysisConfig riskAnalysisConfig;

    @Test
    void shouldReturnInsuranceAnalysis() {
        CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO = CreateInsuranceAnalysisDTOStub.create();
        given(riskAnalysisConfig.getStartIneligibleAge()).willReturn(60);
        given(riskAnalysisConfig.getHigherRiskPointsByAge()).willReturn(-2L);
        given(riskAnalysisConfig.getLowerRiskPointsByAge()).willReturn(-1L);
        given(riskAnalysisConfig.getStartAgeToIgnoreRiskPoints()).willReturn(40);
        given(riskAnalysisConfig.getFinishAgeToUpgradeRiskPoints()).willReturn(30);
        given(riskAnalysisConfig.getStartIncomeToDowngradeRiskPoints()).willReturn(200000);
        given(riskAnalysisConfig.getDowngradeRiskPointsByIncome()).willReturn(-1L);
        given(riskAnalysisConfig.getUpgradeRiskPointsByHouse()).willReturn(1L);
        given(riskAnalysisConfig.getMaxVehicleAgeToUpgradeRiskPoints()).willReturn(5);

        InsuranceAnalysisDTO insuranceAnalysisDTO = insuranceService.analysisInsurance(createInsuranceAnalysisDTO).block();

        assertNotNull(insuranceAnalysisDTO);
        assertEquals(InsuranceAnalysisEnum.RESPONSIBLE, insuranceAnalysisDTO.getAuto());
        assertEquals(InsuranceAnalysisEnum.INELIGIBLE, insuranceAnalysisDTO.getDisability());
        assertEquals(InsuranceAnalysisEnum.ECONOMIC, insuranceAnalysisDTO.getHome());
        assertEquals(InsuranceAnalysisEnum.REGULAR, insuranceAnalysisDTO.getLife());
    }

}