package com.gabrielspassos.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielspassos.enumerator.MaritalStatusEnum;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateInsuranceAnalysisRequest {

    @Min(value = 0, message = "Minimal age is 0")
    private Integer age;

    @Min(value = 0, message = "Minimal dependents is 0")
    private Integer dependents;

    @Nullable
    private CreateInsuranceAnalysisHouseRequest house;

    @Min(value = 0, message = "Minimal income is 0")
    private Integer income;

    @JsonProperty("marital_status")
    private MaritalStatusEnum maritalStatus;

    @Size(min = 3, max = 3, message = "Needed 3 risk questions answers")
    @JsonProperty("risk_questions")
    private List<Boolean> riskQuestions;

    @Nullable
    private CreateInsuranceAnalysisVehicleRequest vehicle;

}
