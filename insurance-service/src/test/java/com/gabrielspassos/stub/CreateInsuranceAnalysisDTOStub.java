package com.gabrielspassos.stub;

import com.gabrielspassos.dto.CreateInsuranceAnalysisDTO;
import com.gabrielspassos.dto.CreateInsuranceAnalysisHouseDTO;
import com.gabrielspassos.dto.CreateInsuranceAnalysisVehicleDTO;
import com.gabrielspassos.enumerator.MaritalStatusEnum;
import com.gabrielspassos.enumerator.OwnershipStatusEnum;
import org.assertj.core.util.Lists;

public class CreateInsuranceAnalysisDTOStub {

    public static CreateInsuranceAnalysisDTO create() {
        CreateInsuranceAnalysisDTO createInsuranceAnalysisDTO = new CreateInsuranceAnalysisDTO();
        createInsuranceAnalysisDTO.setAge(35L);
        createInsuranceAnalysisDTO.setDependents(2L);
        createInsuranceAnalysisDTO.setHouse(createHouse(OwnershipStatusEnum.OWNED));
        createInsuranceAnalysisDTO.setIncome(0L);
        createInsuranceAnalysisDTO.setMaritalStatus(MaritalStatusEnum.MARRIED);
        createInsuranceAnalysisDTO.setRiskQuestions(Lists.newArrayList(false, true, false));
        createInsuranceAnalysisDTO.setVehicle(createVehicle(2018));
        return createInsuranceAnalysisDTO;
    }

    private static CreateInsuranceAnalysisHouseDTO createHouse(OwnershipStatusEnum ownershipStatus) {
        CreateInsuranceAnalysisHouseDTO createInsuranceAnalysisHouseDTO = new CreateInsuranceAnalysisHouseDTO();
        createInsuranceAnalysisHouseDTO.setStatus(ownershipStatus);
        return createInsuranceAnalysisHouseDTO;
    }

    private static CreateInsuranceAnalysisVehicleDTO createVehicle(Integer year) {
        CreateInsuranceAnalysisVehicleDTO createInsuranceAnalysisVehicleDTO = new CreateInsuranceAnalysisVehicleDTO();
        createInsuranceAnalysisVehicleDTO.setYear(year);
        return createInsuranceAnalysisVehicleDTO;
    }
}
