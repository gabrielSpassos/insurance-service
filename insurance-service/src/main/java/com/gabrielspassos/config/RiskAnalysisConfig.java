package com.gabrielspassos.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RiskAnalysisConfig {

    // Age
    @Value("${insurance.risk.age.ineligible}")
    private Integer startIneligibleAge;

    @Value("${insurance.risk.age.start-ignore-risk}")
    private Integer startAgeToIgnoreRiskPoints;

    @Value("${insurance.risk.age.finish-upgrade-risk}")
    private Integer finishAgeToUpgradeRiskPoints;

    @Value("${insurance.risk.age.points.higher}")
    private Long higherRiskPointsByAge;

    @Value("${insurance.risk.age.points.lower}")
    private Long lowerRiskPointsByAge;

    // Income
    @Value("${insurance.risk.income.start-downgrade-risk}")
    private Integer startIncomeToDowngradeRiskPoints;

    @Value("${insurance.risk.income.points.downgrade}")
    private Long downgradeRiskPointsByIncome;

    // House
    @Value("${insurance.risk.house.points.upgrade}")
    private Long upgradeRiskPointsByHouse;

    // Vehicle
    @Value("${insurance.risk.vehicle.max-age-upgrade-risk}")
    private Integer maxVehicleAgeToUpgradeRiskPoints;

}
