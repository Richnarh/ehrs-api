/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.lab.LabResult;
import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.mapper.LabResultMapper;
import com.khoders.ehrs.payload.LabResultDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.Msg;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author pascal
 */
@Stateless
public class LabResultService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private LabResultMapper labResultMapper;
    @Inject private AppService appService;
   
    
    public LabResultDto save(LabResultDto dto,String patientId, String userAccountId)
    {
        if (dto.getId() != null){
            LabResult emp = crudApi.find(LabResult.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("LabResult with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        LabResult labResult = labResultMapper.toEntity(dto);
        labResult.setUserAccount(appService.getUser(userAccountId));
        labResult.setPatient(appService.getPatient(patientId));
        if(crudApi.save(labResult) != null)
        {
            return labResultMapper.toDto(labResult);
        }
        return null;
    }
    
    public LabResultDto findById(String labResultId)
    {
       LabResult labResult = crudApi.find(LabResult.class, labResultId);
       if (labResult == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return labResultMapper.toDto(labResult);
    }
    
    public LabResultDto labResultList(String patientId,String labTestId){
        Patient patient = appService.getPatient(patientId);
        LabTest lbTest = crudApi.find(LabTest.class, labTestId);
        String sql = "SELECT e FROM LabResult e WHERE e.patient = :patient AND e.labTest = :labTest";
        Query testResult =  crudApi.getEm().createQuery(sql)
                .setParameter("patient", patient)
                .setParameter("labTest", lbTest);
        LabResult labTestResult = (LabResult)testResult.getResultStream().findFirst().orElse(null);
        if(labTestResult != null)
            return labResultMapper.toDto(labTestResult);
        return null;  
    }
    
    public boolean delete(String labResultId)
    {
        LabResult labResult = crudApi.find(LabResult.class, labResultId);
        if(labResult != null){
            return crudApi.delete(labResult);
        }
        return false;
    }
}
