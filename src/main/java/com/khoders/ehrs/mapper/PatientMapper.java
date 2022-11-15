/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.commons.AssignDrStatus;
import com.khoders.ehrs.commons.AssignPatient;
import com.khoders.ehrs.commons.PatientCategory;
import com.khoders.ehrs.commons.Source;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.patient.AssignDr;
import com.khoders.ehrs.entity.patient.Complain;
import com.khoders.ehrs.entity.patient.DrReport;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.patient.PatientVital;
import com.khoders.ehrs.entity.patient.Prescription;
import com.khoders.ehrs.entity.pharmacy.Inventory;
import com.khoders.ehrs.entity.settings.Frequency;
import com.khoders.ehrs.payload.patient.AssignDrDto;
import com.khoders.ehrs.payload.patient.DrReportDto;
import com.khoders.ehrs.payload.patient.PatientDto;
import com.khoders.ehrs.payload.patient.PatientVitalDto;
import com.khoders.ehrs.payload.patient.PrescriptionDto;
import com.khoders.resource.enums.Gender;
import com.khoders.resource.enums.IdType;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import java.time.LocalDateTime;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class PatientMapper
{
    @Inject private CrudApi crudApi;
    
    public Patient toEntity(PatientDto dto){
        Patient patient = new Patient();
        if (dto.getId() != null){
            patient.setId(dto.getId());
        }
        patient.setFullname(dto.getFullname());
        patient.setOpdNumber(dto.getOpdNumber());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setPatientCategory(PatientCategory.valueOf(dto.getPatientCategory()));
        patient.setIdType(IdType.valueOf(dto.getIdType()));
        patient.setIdNumber(dto.getIdNumber());
        patient.setGender(Gender.valueOf(dto.getGender()));
        patient.genCode();
        patient.setAge(dto.getAge());
        patient.setOccupation(dto.getOccupation());
        return patient;
    }
    
    public PatientDto toDto(Patient patient){
        PatientDto dto = new PatientDto();
        if(patient.getId() == null) return null;
        dto.setId(patient.getId());
        dto.setFullname(patient.getFullname());
        dto.setOpdNumber(patient.getOpdNumber());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setAddress(patient.getAddress());
        dto.setIdType(patient.getIdType().getLabel());
        dto.setIdNumber(patient.getIdNumber());
        dto.setGender(patient.getGender().getLabel());
        dto.setPatientCategory(patient.getPatientCategory().getLabel());
        dto.setRefNo(patient.getRefNo());
        dto.setAge(patient.getAge());
        dto.setOccupation(patient.getOccupation());
        dto.setValueDate(DateUtil.parseLocalDateString(patient.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }

    public PatientVital toEntity(PatientVitalDto dto)
    {
       PatientVital pv = new PatientVital();
       if (dto.getId() != null){
            pv.setId(dto.getId());
        }
       pv.setBp(dto.getBp());
       pv.setComment(dto.getComment());
       pv.setPulse(dto.getPulse());
       pv.setSpTwo(dto.getSpTwo());
       pv.setTemp(dto.getTemp());
       pv.setVitalDate(LocalDateTime.now());
       pv.setWeight(dto.getWeight());
       if(dto.getPatientId() == null){
           throw new DataNotFoundException("Specify a valid Physician Id");
       }
       Employee phsycian = crudApi.find(Employee.class, dto.getPatientId());
       if(phsycian != null){
           pv.setPhysician(phsycian);
       }
       pv.setSource(Source.valueOf(dto.getSource()));
       pv.genCode();
       return pv;
    }
    public PatientVitalDto toDto(PatientVital pv)
    {
       PatientVitalDto dto = new PatientVitalDto();
       if (pv.getId() != null){
            dto.setId(pv.getId());
        }
       dto.setBp(pv.getBp());
       dto.setComment(pv.getComment());
       dto.setPulse(pv.getPulse());
       dto.setSpTwo(pv.getSpTwo());
       dto.setTemp(pv.getTemp());
       dto.setVitalDate(DateUtil.localDateTimeToString(pv.getVitalDate(), Pattern.ddMMyyyyhma));
       dto.setWeight(pv.getWeight());
       if(pv.getPatient() == null){
           dto.setPatientId(pv.getPatient().getId());
           dto.setPatientName(pv.getPatient().getFullname());
       }
       if(pv.getPhysician() != null){
           dto.setPhysician(pv.getPhysician().getFirstname() +" "+pv.getPhysician().getLastname());
           dto.setPhysicianId(pv.getPhysician().getId());
       }
       dto.setSource(pv.getSource() != null ? pv.getSource().name() : "-");
       return dto;
    }

    public AssignDr toEntity(AssignDrDto dto)
    {
        AssignDr dr = new AssignDr();
       if (dto.getId() != null){
            dr.setId(dto.getId());
        }
       if(dto.getDoctorId() == null){
          throw new DataNotFoundException("Specify a valid DR id");
       }
        Employee employee = crudApi.find(Employee.class, dto.getDoctorId());
        if(employee != null){
            dr.setDoctor(employee);
        }
        dr.setNote(dto.getNote());
        dr.setAssignDrStatus(AssignDrStatus.UNATTENDED);
        dr.genCode();
        return dr;
    }

    public AssignDrDto toDto(AssignDr dr)
    {
        AssignDrDto dto = new AssignDrDto();
       if (dr.getId() != null){
            dto.setId(dr.getId());
        }
       dto.setNote(dr.getNote());
       if(dr.getDoctor() != null){
           dto.setDoctor(dr.getDoctor().getFirstname() +" "+dr.getDoctor().getLastname());
           dto.setDoctorId(dr.getDoctor().getId());
       }
       if(dr.getPatient() != null){
           dto.setPatientName(dr.getPatient().getFullname());
           dto.setOpdNumber(dr.getPatient().getOpdNumber());
           dto.setPatientId(dr.getPatient().getId());
       }
       dto.setValueDate(DateUtil.parseLocalDateString(dr.getValueDate(), Pattern.ddMMyyyy));
       dto.setAssignDrStatus(dr.getAssignDrStatus().getLabel());
       dto.setAssignTime(dr.getCreatedDate().getHour() +":"+dr.getCreatedDate().getMinute());
       return dto;
    }

    public Prescription toEntity(PrescriptionDto dto)
    {
       Prescription prescription = new Prescription();
       if(dto.getId() != null){
            prescription.setId(dto.getId());
       }
       if(dto.getDrReportId() == null){
          throw new DataNotFoundException("Specify a valid DrReport Id");
       }
        if(dto.getPatientId() == null){
          throw new DataNotFoundException("Specify a valid Patient Id");
       }
       if(dto.getFrequencyId() == null){
          throw new DataNotFoundException("Specify a valid Frequency Id");
       }
       if(dto.getInventoryId() == null){
          throw new DataNotFoundException("Specify a valid Product Id");
       }
        DrReport report = crudApi.find(DrReport.class, dto.getDrReportId());
        Patient patient = crudApi.find(Patient.class, dto.getPatientId());
        Frequency frequency = crudApi.find(Frequency.class, dto.getFrequencyId());
        Inventory inventory = crudApi.find(Inventory.class, dto.getInventoryId());
        
        if(report != null)
            prescription.setDrReport(report);
        if(patient != null)
            prescription.setPatient(patient);
        if(frequency != null)
            prescription.setFrequency(frequency);
        if(inventory != null)
            prescription.setInventory(inventory);
        
        prescription.setDose(dto.getDose());
        prescription.setNotes(dto.getNotes());
        prescription.genCode();
        prescription.setPrice(dto.getPrice());
        return prescription;
    }
        
    public PrescriptionDto toDto(Prescription prescription){
      PrescriptionDto dto = new PrescriptionDto();  
      
      if(prescription.getId() == null) return null;  
       dto.setId(prescription.getId());
       dto.setDose(prescription.getDose());
       dto.setNotes(prescription.getNotes());
       dto.setRefNo(prescription.getRefNo());
       dto.setPrice(prescription.getPrice());
       if(prescription.getDrReport() != null){
           dto.setDrReport(prescription.getDrReport().getComment());
           dto.setDrReportId(prescription.getDrReport().getId());
       }
       if(prescription.getPatient() != null){
           dto.setPatient(prescription.getPatient().getFullname());
           dto.setPatientId(prescription.getPatient().getId());
       }
       if(prescription.getFrequency() != null){
           dto.setFrequency(prescription.getFrequency().getFrequencyName());
           dto.setFrequencyId(prescription.getFrequency().getId());
       }
       if(prescription.getInventory()!= null){
           dto.setInventory(prescription.getInventory().getProductName());
           dto.setInventoryId(prescription.getInventory().getId());
       }
       dto.setValueDate(DateUtil.parseLocalDateString(prescription.getValueDate(), Pattern.ddMMyyyy));
       return dto;
    }

   public DrReport toEntity(DrReportDto dto){
        DrReport drReport = new DrReport();
        if (dto.getId() != null){
            drReport.setId(dto.getId());
        }   
        drReport.setComment(dto.getComment());
        if(dto.getComplainId() == null){
          throw new DataNotFoundException("Specify a valid Complain Id");
       }
        Complain complain = crudApi.find(Complain.class, dto.getComplainId());
        if(complain != null)
            drReport.setComplain(complain);
        try
        {
            drReport.setAssignPatient(AssignPatient.valueOf(dto.getAssignPatient()));
        } catch (Exception e)
        {
        }
        drReport.genCode();
        return drReport;
    }
    
    public DrReportDto toDto(DrReport drReport){
        DrReportDto dto = new DrReportDto();
        if(drReport.getId() == null) return null;
        dto.setId(drReport.getId());
        
        dto.setComment(drReport.getComment());
        dto.setRefNo(drReport.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(drReport.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
