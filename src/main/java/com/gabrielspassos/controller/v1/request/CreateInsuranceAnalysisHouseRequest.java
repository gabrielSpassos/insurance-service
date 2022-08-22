package com.gabrielspassos.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielspassos.enumerator.OwnershipStatusEnum;
import lombok.Data;

@Data
public class CreateInsuranceAnalysisHouseRequest {

    @JsonProperty("ownership_status")
    private OwnershipStatusEnum status;

}
