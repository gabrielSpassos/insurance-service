package com.gabrielspassos.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielspassos.enumerator.MaritalStatusEnum;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateInsuranceAnalysisRequest {

    @NotNull(message = "Age must be informed")
    @Min(value = 0L, message = "Minimal age is 0")
    private Long age;

    @NotNull(message = "Dependents must be informed")
    @Min(value = 0L, message = "Minimal dependents is 0")
    private Long dependents;

    @Nullable
    private CreateInsuranceAnalysisHouseRequest house;

    @NotNull(message = "Income must be informed")
    @Min(value = 0L, message = "Minimal income is 0")
    private Long income;

    @NotNull(message = "Marital status must be informed")
    @JsonProperty("marital_status")
    private MaritalStatusEnum maritalStatus;

    @NotNull(message = "Risk questions must be informed")
    @Size(min = 3, max = 3, message = "Needed 3 risk questions answers")
    @JsonProperty("risk_questions")
    private List<Boolean> riskQuestions;

    @Nullable
    private CreateInsuranceAnalysisVehicleRequest vehicle;

}
