package com.gabrielspassos.stub;

import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;

public class InsuranceAnalysisDTOStub {

    public static InsuranceAnalysisDTO create() {
        InsuranceAnalysisDTO insuranceAnalysisDTO = new InsuranceAnalysisDTO();
        insuranceAnalysisDTO.setAuto(InsuranceAnalysisEnum.REGULAR);
        insuranceAnalysisDTO.setDisability(InsuranceAnalysisEnum.INELIGIBLE);
        insuranceAnalysisDTO.setHome(InsuranceAnalysisEnum.ECONOMIC);
        insuranceAnalysisDTO.setLife(InsuranceAnalysisEnum.REGULAR);
        return insuranceAnalysisDTO;
    }
}
