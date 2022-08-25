package com.gabrielspassos.dto;

import com.gabrielspassos.enumerator.MaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInsuranceAnalysisDTO {

    private Long age;
    private Long dependents;
    private CreateInsuranceAnalysisHouseDTO house;
    private Long income;
    private MaritalStatusEnum maritalStatus;
    private List<Boolean> riskQuestions;
    private CreateInsuranceAnalysisVehicleDTO vehicle;
}
