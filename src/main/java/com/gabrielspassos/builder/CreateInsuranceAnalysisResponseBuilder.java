package com.gabrielspassos.builder;

import com.gabrielspassos.controller.v1.response.CreateInsuranceAnalysisResponse;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;

import static com.gabrielspassos.enumerator.InsuranceAnalysisEnum.toStatus;

public class CreateInsuranceAnalysisResponseBuilder {

    public static CreateInsuranceAnalysisResponse build(InsuranceAnalysisDTO insuranceAnalysisDTO) {
        return CreateInsuranceAnalysisResponse.builder()
                .auto(toStatus(insuranceAnalysisDTO.getAuto()))
                .disability(toStatus(insuranceAnalysisDTO.getDisability()))
                .home(toStatus(insuranceAnalysisDTO.getHome()))
                .life(toStatus(insuranceAnalysisDTO.getLife()))
                .build();
    }
}
