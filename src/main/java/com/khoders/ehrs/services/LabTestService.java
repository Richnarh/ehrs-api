/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.mapper.LabTestMapper;
import com.khoders.ehrs.payload.LabTestDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.Pattern;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class LabTestService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private LabTestMapper labTestMapper;
    @Inject private AppService appService;
   
    
    public LabTestDto save(LabTestDto dto, String userAccountId, String employeeId)
    {
        if (dto.getId() != null){
            LabTest emp = crudApi.find(LabTest.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("LabTest with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        LabTest labTest = labTestMapper.toEntity(dto);
        labTest.setUserAccount(appService.getUser(userAccountId));
        labTest.setEmployee(appService.getEmployee(employeeId));
        labTest.setPatient(appService.getPatient(dto.getPatientId()));
        if(crudApi.save(labTest) != null)
        {
            return labTestMapper.toDto(labTest);
        }
        return null;
    }
    
    public LabTestDto findById(String labTestId)
    {
       LabTest labTest = crudApi.find(LabTest.class, labTestId);
       if (labTest == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return labTestMapper.toDto(labTest);
    }
    
    public List<LabTestDto> labTestList(){
        List<LabTestDto> dtoList = new LinkedList<>();
        List<LabTest> labTestList = builder.findAll(LabTest.class);
        for (LabTest labTest : labTestList)
        {
            dtoList.add(labTestMapper.toDto(labTest));
        }
        return dtoList;
    }
    
    public boolean delete(String labTestId)
    {
        LabTest labTest = crudApi.find(LabTest.class, labTestId);
        if(labTest != null){
            return crudApi.delete(labTest);
        }
        return false;
    }
}
