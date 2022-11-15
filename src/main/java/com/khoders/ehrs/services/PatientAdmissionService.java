/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.patient.PatientAdmission;
import com.khoders.ehrs.entity.patient.Prescription;
import com.khoders.ehrs.mapper.PatientAdmissionMapper;
import com.khoders.ehrs.payload.patient.PatientAdmissionDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.Msg;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class PatientAdmissionService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private PatientAdmissionMapper patientAdmissionMapper;
    @Inject private AppService appService;
   
    
    public PatientAdmissionDto save(PatientAdmissionDto dto, String userAccountId, String patientId){
        if (dto.getId() != null){
            PatientAdmission patientAdmission = crudApi.find(PatientAdmission.class, dto.getId());
            if (patientAdmission == null){
                throw new DataNotFoundException("PatientAdmission with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        PatientAdmission patientAdmission = patientAdmissionMapper.toEntity(dto);
        patientAdmission.setUserAccount(appService.getUser(userAccountId));
        patientAdmission.setPatient(appService.getPatient(patientId));
        if(crudApi.save(patientAdmission) != null)
        {
            return patientAdmissionMapper.toDto(patientAdmission);
        }
        return null;
    }
    
    public PatientAdmissionDto findById(String patientId,String patientAdmissionId){
       PatientAdmission patientAdmission = findByAdmissionId(patientId,patientAdmissionId);
       if (patientAdmission == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return patientAdmissionMapper.toDto(patientAdmission);
    }
    
    private PatientAdmission findByAdmissionId(String patientId, String patientAdmissionId){
        Patient patient = appService.getPatient(patientId);
        if(patient != null)
            return crudApi.getEm().createQuery("SELECT e FROM PatientAdmission e WHERE e.patient=:patient AND e.id=:patientAdmissionId", PatientAdmission.class)
                    .setParameter("patient", patient)
                    .setParameter("patientAdmissionId", patientAdmissionId)
                    .getSingleResult();
        else
            return null;
    }
    
    public List<PatientAdmissionDto> patientAdmissionList(String patientId){
        List<PatientAdmissionDto> dtoList = new LinkedList<>();
        Patient patient = appService.getPatient(patientId);
        if(patient != null){
            List<PatientAdmission> drReportList = crudApi.getEm().createQuery("SELECT e FROM PatientAdmission e WHERE e.patient=:patient", PatientAdmission.class)
                        .setParameter("patient", patient)
                        .getResultList();

            for (PatientAdmission admission : drReportList){
                dtoList.add(patientAdmissionMapper.toDto(admission));
            }
        }
        return dtoList; 
    }
    
    public boolean delete(String patientId,String patientAdmissionId){
        PatientAdmission patientAdmission = findByAdmissionId(patientId, patientAdmissionId);
        if(patientAdmission != null)
            return crudApi.delete(patientAdmission);
        return false;
    }
}
