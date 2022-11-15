/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.patient.Ward;
import com.khoders.ehrs.entity.lab.Lab;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.patient.PatientAdmission;
import com.khoders.ehrs.payload.patient.PatientAdmissionDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import java.time.LocalDate;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class PatientAdmissionMapper
{
    @Inject private CrudApi crudApi;
    
    public PatientAdmission toEntity(PatientAdmissionDto dto){
        PatientAdmission patientAdmission = new PatientAdmission();
        if (dto.getId() != null){
            patientAdmission.setId(dto.getId());
        }
        if(dto.getLabId() == null){
            throw new DataNotFoundException("Please Specify Lab Id");
        }
        if(dto.getPatientId() == null){
            throw new DataNotFoundException("Please Specify Patient Id");
        }
        if(dto.getRoomId() == null){
            throw new DataNotFoundException("Please Specify Room Id");
        }
        Lab lab = crudApi.find(Lab.class, dto.getLabId());
        if(lab != null){
            patientAdmission.setLab(lab);
        }
        Patient patient = crudApi.find(Patient.class, dto.getPatientId());
        if(patient != null){
            patientAdmission.setPatient(patient);
        }
        Ward room = crudApi.find(Ward.class, dto.getRoomId());
        if(room != null){
            patientAdmission.setWard(room);
        }
        patientAdmission.setAdmissionDate(dto.getAdmissionDate() != null ? DateUtil.parseLocalDate(dto.getAdmissionDate(), Pattern._yyyyMMdd) : LocalDate.now());
        patientAdmission.setNoOfDays(dto.getNoOfDays());
        patientAdmission.genCode();
        patientAdmission.setPrice(dto.getPrice());
        return patientAdmission;
    }
    
    public PatientAdmissionDto toDto(PatientAdmission patientAdmission){
        PatientAdmissionDto dto = new PatientAdmissionDto();
        if(patientAdmission.getId() == null) return null;
        dto.setId(patientAdmission.getId());
        if(patientAdmission.getPatient() != null){
            dto.setPatientId(patientAdmission.getPatient().getId());
            dto.setPatientName(patientAdmission.getPatient().getFullname());
        }
        if(patientAdmission.getLab() != null){
            dto.setLabId(patientAdmission.getLab().getId());
            dto.setLabName(patientAdmission.getLab().getUnitName());
        }
        if(patientAdmission.getWard() != null){
            dto.setRoomId(patientAdmission.getWard().getId());
            dto.setRoomName(patientAdmission.getWard().getWardType() != null ? patientAdmission.getWard().getWardType().getWardName() : "");
        }
        dto.setAdmissionDate(DateUtil.parseLocalDateString(patientAdmission.getAdmissionDate(), Pattern._ddMMyyyy));
        dto.setRefNo(patientAdmission.getRefNo());
        dto.setNoOfDays(patientAdmission.getNoOfDays());
        dto.setPrice(patientAdmission.getPrice());
        dto.setValueDate(DateUtil.parseLocalDateString(patientAdmission.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
