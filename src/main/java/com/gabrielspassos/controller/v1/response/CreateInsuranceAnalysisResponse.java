package com.gabrielspassos.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInsuranceAnalysisResponse {

    private String auto;
    private String disability;
    private String home;
    private String life;

}
