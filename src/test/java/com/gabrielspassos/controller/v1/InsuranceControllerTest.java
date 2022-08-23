package com.gabrielspassos.controller.v1;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.service.InsuranceService;
import com.gabrielspassos.stub.CreateInsuranceAnalysisDTOStub;
import com.gabrielspassos.stub.InsuranceAnalysisDTOStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InsuranceControllerTest {

    private WebTestClient webTestClient;

    @Mock
    private InsuranceService insuranceService;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient
                .bindToController(new InsuranceController(insuranceService, modelMapper))
                .configureClient()
                .build();
    }

    @Test
    void shouldCreateInsuranceAnalysis() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request/create-insurance-analysis-request.json")));
        String output = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/create-insurance-analysis-response.json")));

        CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO = CreateInsuranceAnalysisDTOStub.create();
        InsuranceAnalysisDTO insuranceAnalysisDTO = InsuranceAnalysisDTOStub.create();

        given(insuranceService.analysisInsurance(createInsuranceAnalysisDTO))
                .willReturn(Mono.just(insuranceAnalysisDTO));

        webTestClient.post()
                .uri("/v1/insurances-analysis")
                .header("content-type", "application/json")
                .bodyValue(input)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(output);
    }
}