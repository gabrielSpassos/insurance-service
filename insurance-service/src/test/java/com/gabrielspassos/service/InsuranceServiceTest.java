package com.gabrielspassos.service;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import com.gabrielspassos.stub.CreateInsuranceAnalysisDTOStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InsuranceServiceTest {

    @InjectMocks
    private InsuranceService insuranceService;

    @Test
    void shouldReturnInsuranceAnalysis() {
        CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO = CreateInsuranceAnalysisDTOStub.create();

        InsuranceAnalysisDTO insuranceAnalysisDTO = insuranceService.analysisInsurance(createInsuranceAnalysisDTO).block();

        assertNotNull(insuranceAnalysisDTO);
        assertEquals(InsuranceAnalysisEnum.RESPONSIBLE, insuranceAnalysisDTO.getAuto());
        assertEquals(InsuranceAnalysisEnum.INELIGIBLE, insuranceAnalysisDTO.getDisability());
        assertEquals(InsuranceAnalysisEnum.ECONOMIC, insuranceAnalysisDTO.getHome());
        assertEquals(InsuranceAnalysisEnum.REGULAR, insuranceAnalysisDTO.getLife());
    }

}