package com.gabrielspassos.service;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import static java.util.Objects.isNull;

@Slf4j
@Service
@AllArgsConstructor
public class InsuranceService {

    private static final Integer ZERO = 0;
    private static final Integer START_INELIGIBLE_AGE = 60;

    public Mono<InsuranceAnalysisDTO> analysisInsurance(CreateInsuranceAnalysisDTO createInsuranceAnalysis) {
        return Mono.just(createInsuranceAnalysis)
                .map(this::createAnalysis)
                .map(this::analysisIneligibleIncome)
                .map(this::analysisIneligibleVehicles)
                .map(this::analysisIneligibleHouses)
                .map(this::analysisIneligibleAge)
                .map(Tuple2::getT2);
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> createAnalysis(CreateInsuranceAnalysisDTO createInsuranceAnalysis) {
        return Tuples.of(createInsuranceAnalysis, new InsuranceAnalysisDTO());
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisIneligibleIncome(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        if (isNull(tuple.getT1().getIncome()) || ZERO.equals(tuple.getT1().getIncome())) {
            tuple.getT2().setDisability(InsuranceAnalysisEnum.INELIGIBLE);
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisIneligibleVehicles(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        if (isNull(tuple.getT1().getVehicle())) {
            tuple.getT2().setAuto(InsuranceAnalysisEnum.INELIGIBLE);
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisIneligibleHouses(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        if (isNull(tuple.getT1().getHouse()) || isNull(tuple.getT1().getHouse().getStatus())) {
            tuple.getT2().setHome(InsuranceAnalysisEnum.INELIGIBLE);
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisIneligibleAge(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        if (tuple.getT1().getAge() > START_INELIGIBLE_AGE) {
            tuple.getT2().setDisability(InsuranceAnalysisEnum.INELIGIBLE);
            tuple.getT2().setLife(InsuranceAnalysisEnum.INELIGIBLE);
        }

        return tuple;
    }
}
