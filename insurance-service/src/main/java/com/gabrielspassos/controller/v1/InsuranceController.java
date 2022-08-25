package com.gabrielspassos.controller.v1;

import com.gabrielspassos.builder.CreateInsuranceAnalysisResponseBuilder;
import com.gabrielspassos.controller.v1.request.CreateInsuranceAnalysisRequest;
import com.gabrielspassos.controller.v1.response.CreateInsuranceAnalysisResponse;
import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.service.InsuranceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class InsuranceController implements BaseVersion {

    private InsuranceService insuranceService;
    private ModelMapper modelMapper;

    @PostMapping("/insurances-analysis")
    public Mono<CreateInsuranceAnalysisResponse> createInsuranceAnalysis(@RequestBody @Valid CreateInsuranceAnalysisRequest request) {
        log.info("Started insurance analysis with: {}", request);
        CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO = modelMapper.map(request, CreateInsuranceAnalysisDTO.class);
        return insuranceService.analysisInsurance(createInsuranceAnalysisDTO)
                .map(CreateInsuranceAnalysisResponseBuilder::build)
                .doOnSuccess(response -> log.info("Finished insurance analysis: {}", response));
    }
}
