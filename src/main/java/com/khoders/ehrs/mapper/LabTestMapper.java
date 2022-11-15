/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.lab.Lab;
import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.lab.TestType;
import com.khoders.ehrs.payload.LabTestDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class LabTestMapper
{
    @Inject private CrudApi crudApi;
    
    public LabTest toEntity(LabTestDto dto){
        LabTest labTest = new LabTest();
        if (dto.getId() != null)
        {
            labTest.setId(dto.getId());
        }
        if(dto.getLabId() == null){
            throw new DataNotFoundException("Please Specify Lab Id");
        }
        if(dto.getTestTypeId() == null){
            throw new DataNotFoundException("Please Specify Test Type Id");
        }
        Lab lab = crudApi.find(Lab.class, dto.getLabId());
        if(lab != null){
            labTest.setLab(lab);
        }
        
        TestType testType = crudApi.find(TestType.class, dto.getTestTypeId());
        if(testType != null){
            labTest.setTestType(testType);
        }
        
        labTest.setTestDate(DateUtil.parseLocalDate(dto.getTestDate(), Pattern._yyyyMMdd));
        labTest.genCode();
            
        return labTest;
    }
    
    public LabTestDto toDto(LabTest labTest){
        LabTestDto dto = new LabTestDto();
        if(labTest.getId() == null) return null;
        dto.setId(labTest.getId());
        
        if(labTest.getLab()!= null){
            dto.setLabId(labTest.getLab().getId());
            dto.setLabName(labTest.getLab().getUnitName());
        }
        if(labTest.getPatient()!= null){
            dto.setPatientId(labTest.getPatient().getId());
            dto.setPatientName(labTest.getPatient().getFullname());
        }
        if(labTest.getTestType()!= null){
            dto.setTestType(labTest.getTestType().getTestTypeName());
            dto.setTestTypeId(labTest.getTestType().getId());
        }
        if(labTest.getEmployee()!= null){
            dto.setDoctorId(labTest.getEmployee().getId());
            dto.setDoctorName(labTest.getEmployee().getFirstname() +" "+labTest.getEmployee().getLastname());
        }
        dto.setTestDate(DateUtil.parseLocalDateString(labTest.getTestDate(), Pattern.ddMMyyyy));
        dto.setValueDate(DateUtil.parseLocalDateString(labTest.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
