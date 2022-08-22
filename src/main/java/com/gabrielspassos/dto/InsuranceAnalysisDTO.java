package com.gabrielspassos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceAnalysisDTO {

    private String auto;
    private String disability;
    private String home;
    private String life;

}
