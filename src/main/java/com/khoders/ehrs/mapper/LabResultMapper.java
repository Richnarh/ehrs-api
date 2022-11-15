/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.lab.LabResult;
import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.payload.LabResultDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class LabResultMapper
{
    @Inject private CrudApi crudApi;
    
    public LabResult toEntity(LabResultDto dto){
        LabResult labResult = new LabResult();
        if (dto.getId() != null)
        {
            labResult.setId(dto.getId());
        }
        if(dto.getLabTestId() == null){
            throw new DataNotFoundException("Please Specify LabTest Id");
        }
        LabTest labTest = crudApi.find(LabTest.class, dto.getLabTestId());
        if(labTest != null){
            labResult.setLabTest(labTest);
        }
        labResult.setTestResult(dto.getTestResult());
        labResult.genCode();
        labResult.setPrice(dto.getPrice());
        return labResult;
    }
    
    public LabResultDto toDto(LabResult labResult){
        LabResultDto dto = new LabResultDto();
        if(labResult.getId() == null) return null;
        dto.setId(labResult.getId());
        
        if(labResult.getLabTest()!= null){
            dto.setLabTestId(labResult.getLabTest().getId());
            dto.setLabTest(labResult.getLabTest().getLab() != null ? labResult.getLabTest().getLab().getUnitName() : "");
        }
        if(labResult.getPatient()!= null){
            dto.setPatientId(labResult.getPatient().getId());
            dto.setPatientName(labResult.getPatient().getFullname());
        }
        dto.setPrice(labResult.getPrice());
        dto.setTestResult(labResult.getTestResult());
        dto.setValueDate(DateUtil.parseLocalDateString(labResult.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
