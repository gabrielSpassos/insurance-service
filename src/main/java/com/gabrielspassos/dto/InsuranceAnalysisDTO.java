package com.gabrielspassos.dto;

import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceAnalysisDTO {

    private InsuranceAnalysisEnum auto;
    private InsuranceAnalysisEnum disability;
    private InsuranceAnalysisEnum home;
    private InsuranceAnalysisEnum life;

}
