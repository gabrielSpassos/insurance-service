package com.gabrielspassos.service;

import com.gabrielspassos.builder.InsuranceAnalysisDTOBuilder;
import com.gabrielspassos.config.RiskAnalysisConfig;
import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.CreateInsuranceAnalysisVehicleDTO;
import com.gabrielspassos.dto.InsuranceAnalysisDTO;
import com.gabrielspassos.enumerator.InsuranceAnalysisEnum;
import com.gabrielspassos.enumerator.MaritalStatusEnum;
import com.gabrielspassos.enumerator.OwnershipStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Year;
import java.util.Objects;

import static com.gabrielspassos.dto.InsuranceAnalysisDTO.calculateRiskPoints;
import static com.gabrielspassos.enumerator.InsuranceAnalysisEnum.getInsuranceAnalysisByRiskPoint;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@AllArgsConstructor
public class InsuranceService {

    private static final Integer ZERO = 0;

    private RiskAnalysisConfig riskAnalysisConfig;

    public Mono<InsuranceAnalysisDTO> analysisInsurance(CreateInsuranceAnalysisDTO createInsuranceAnalysis) {
        return Mono.just(createInsuranceAnalysis)
                .map(this::createAnalysis)
                .map(this::analysisIneligibleIncome)
                .map(this::analysisIneligibleVehicles)
                .map(this::analysisIneligibleHouses)
                .map(this::analysisIneligibleAge)
                .map(this::analysisRiskByAge)
                .map(this::analysisRiskByIncome)
                .map(this::analysisRiskByHouse)
                .map(this::analysisRiskByDependents)
                .map(this::analysisRiskByMarital)
                .map(this::analysisRiskByVehicle)
                .map(this::calculateRisks);
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> createAnalysis(CreateInsuranceAnalysisDTO createInsuranceAnalysis) {
        return Tuples.of(createInsuranceAnalysis, InsuranceAnalysisDTOBuilder.buildInitialAnalysis(createInsuranceAnalysis));
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
        if (tuple.getT1().getAge() > riskAnalysisConfig.getStartIneligibleAge()) {
            tuple.getT2().setDisability(InsuranceAnalysisEnum.INELIGIBLE);
            tuple.getT2().setLife(InsuranceAnalysisEnum.INELIGIBLE);
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByAge(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (createInsuranceAnalysis.getAge() > riskAnalysisConfig.getStartAgeToIgnoreRiskPoints()) {
            return tuple;
        }

        Long riskPointsToCalculate = createInsuranceAnalysis.getAge() < riskAnalysisConfig.getFinishAgeToUpgradeRiskPoints()
                ? riskAnalysisConfig.getHigherRiskPointsByAge()
                : riskAnalysisConfig.getLowerRiskPointsByAge();
        insuranceAnalysis.updateAllRiskPoints(riskPointsToCalculate);

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByIncome(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (createInsuranceAnalysis.getIncome() > riskAnalysisConfig.getStartIncomeToDowngradeRiskPoints()) {
            insuranceAnalysis.updateAllRiskPoints(riskAnalysisConfig.getDowngradeRiskPointsByIncome());
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByHouse(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (nonNull(createInsuranceAnalysis.getHouse())
                && nonNull(createInsuranceAnalysis.getHouse().getStatus())
                && OwnershipStatusEnum.MORTGAGED.equals(createInsuranceAnalysis.getHouse().getStatus())) {
            Long upgradeRiskPointsByHouse = riskAnalysisConfig.getUpgradeRiskPointsByHouse();
            insuranceAnalysis.setHomeRiskPoints(
                    calculateRiskPoints(insuranceAnalysis.getHomeRiskPoints(), upgradeRiskPointsByHouse)
            );
            insuranceAnalysis.setDisabilityRiskPoints(
                    calculateRiskPoints(insuranceAnalysis.getDisabilityRiskPoints(), upgradeRiskPointsByHouse)
            );
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByDependents(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (createInsuranceAnalysis.getDependents() > ZERO) {
            insuranceAnalysis.setDisabilityRiskPoints(calculateRiskPoints(insuranceAnalysis.getDisabilityRiskPoints(), 1L));
            insuranceAnalysis.setLifeRiskPoints(calculateRiskPoints(insuranceAnalysis.getLifeRiskPoints(), 1L));
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByMarital(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (nonNull(createInsuranceAnalysis.getMaritalStatus())
                && MaritalStatusEnum.MARRIED.equals(createInsuranceAnalysis.getMaritalStatus())) {
            insuranceAnalysis.setLifeRiskPoints(calculateRiskPoints(insuranceAnalysis.getLifeRiskPoints(), 1L));
            insuranceAnalysis.setDisabilityRiskPoints(calculateRiskPoints(insuranceAnalysis.getDisabilityRiskPoints(), -1L));
        }

        return tuple;
    }

    private Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> analysisRiskByVehicle(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        CreateInsuranceAnalysisDTO createInsuranceAnalysis = tuple.getT1();
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        if (shouldCalculateRiskFromVehicle(createInsuranceAnalysis.getVehicle())) {
            insuranceAnalysis.setAutoRiskPoints(calculateRiskPoints(insuranceAnalysis.getLifeRiskPoints(), 1L));
        }

        return tuple;
    }

    private InsuranceAnalysisDTO calculateRisks(Tuple2<CreateInsuranceAnalysisDTO, InsuranceAnalysisDTO> tuple) {
        InsuranceAnalysisDTO insuranceAnalysis = tuple.getT2();

        InsuranceAnalysisEnum autoInsuranceAnalysis = getInsuranceAnalysis(insuranceAnalysis.getAuto(), insuranceAnalysis.getAutoRiskPoints());
        InsuranceAnalysisEnum disabilityInsuranceAnalysis = getInsuranceAnalysis(insuranceAnalysis.getDisability(), insuranceAnalysis.getDisabilityRiskPoints());
        InsuranceAnalysisEnum homeInsuranceAnalysis = getInsuranceAnalysis(insuranceAnalysis.getHome(), insuranceAnalysis.getHomeRiskPoints());
        InsuranceAnalysisEnum lifeInsuranceAnalysis = getInsuranceAnalysis(insuranceAnalysis.getLife(), insuranceAnalysis.getLifeRiskPoints());

        insuranceAnalysis.setAuto(autoInsuranceAnalysis);
        insuranceAnalysis.setDisability(disabilityInsuranceAnalysis);
        insuranceAnalysis.setHome(homeInsuranceAnalysis);
        insuranceAnalysis.setLife(lifeInsuranceAnalysis);

        return insuranceAnalysis;
    }

    private boolean shouldCalculateRiskFromVehicle(CreateInsuranceAnalysisVehicleDTO vehicleDTO) {
        Integer currentYear = Year.now().getValue();

        return Objects.nonNull(vehicleDTO)
                && (currentYear - vehicleDTO.getYear() <= riskAnalysisConfig.getMaxVehicleAgeToUpgradeRiskPoints());
    }

    private InsuranceAnalysisEnum getInsuranceAnalysis(InsuranceAnalysisEnum currentAnalysis, Long riskPoints) {
        return InsuranceAnalysisEnum.INELIGIBLE.equals(currentAnalysis)
                ? currentAnalysis
                : getInsuranceAnalysisByRiskPoint(riskPoints);
    }

}
