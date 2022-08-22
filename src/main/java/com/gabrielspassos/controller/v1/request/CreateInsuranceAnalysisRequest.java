package com.gabrielspassos.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielspassos.enumerator.MaritalStatusEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
public class CreateInsuranceAnalysisRequest {

    @Min(value = 0, message = "Minimal age is 0")
    private Integer age;

    @Min(value = 0, message = "Minimal dependents is 0")
    private Integer dependents;

    private CreateInsuranceAnalysisHouseRequest house;

    @Min(value = 0, message = "Minimal income is 0")
    private Integer income;

    @JsonProperty("marital_status")
    private MaritalStatusEnum maritalStatus;

    @Min(value = 3, message = "Needed 3 risk questions answers")
    @Max(value = 3, message = "Needed 3 risk questions answers")
    @JsonProperty("risk_questions")
    private List<Boolean> riskQuestions;

    private CreateInsuranceAnalysisVehicleRequest vehicle;

}
