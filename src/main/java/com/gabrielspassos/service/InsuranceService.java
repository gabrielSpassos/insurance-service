package com.gabrielspassos.service;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class InsuranceService {

    public Mono<InsuranceAnalysisDTO> analysisInsurance(CreateInsuranceAnalysisDTO createInsuranceAnalysis) {
        return Mono.just(new InsuranceAnalysisDTO());
    }
}
