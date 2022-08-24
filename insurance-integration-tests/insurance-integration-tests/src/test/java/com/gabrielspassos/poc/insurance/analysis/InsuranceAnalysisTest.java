package com.gabrielspassos.poc.insurance.analysis;

import com.intuit.karate.junit5.Karate;

public class InsuranceAnalysisTest {

    @Karate.Test
    Karate testInsuranceAnalysisScenarios() {
        return Karate.run("insuranceAnalysis").relativeTo(getClass());
    }

}
