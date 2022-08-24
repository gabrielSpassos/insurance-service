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

    private Integer age;
    private Integer dependents;
    private CreateInsuranceAnalysisHouseDTO house;
    private Integer income;
    private MaritalStatusEnum maritalStatus;
    private List<Boolean> riskQuestions;
    private CreateInsuranceAnalysisVehicleDTO vehicle;
}
