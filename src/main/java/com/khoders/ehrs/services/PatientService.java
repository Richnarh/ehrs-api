/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.patient.AssignDr;
import com.khoders.ehrs.entity.patient.Complain;
import com.khoders.ehrs.entity.patient.DrReport;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.patient.PatientVital;
import com.khoders.ehrs.entity.patient.Prescription;
import com.khoders.ehrs.mapper.PatientMapper;
import com.khoders.ehrs.payload.patient.AssignDrDto;
import com.khoders.ehrs.payload.patient.DrReportDto;
import com.khoders.ehrs.payload.patient.PatientDto;
import com.khoders.ehrs.payload.patient.PatientVitalDto;
import com.khoders.ehrs.payload.patient.PrescriptionDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.SystemUtils;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class PatientService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private PatientMapper patientMapper;
    @Inject private AppService appService;
   
    
    public PatientDto save(PatientDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Patient patient = crudApi.find(Patient.class, dto.getId());
            if (patient == null){
                throw new DataNotFoundException("Patient with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Patient patient = patientMapper.toEntity(dto);
        patient.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(patient) != null)
        {
            return patientMapper.toDto(patient);
        }
        return null;
    }
    
    public PatientDto findById(String patientId)
    {
       Patient patient = crudApi.find(Patient.class, patientId);
       return patientMapper.toDto(patient);
    }
    
    public List<PatientDto> patientList(){
        List<PatientDto> dtoList = new LinkedList<>();
        List<Patient> patientList = builder.findAll(Patient.class);
        
        for (Patient patient : patientList){
            dtoList.add(patientMapper.toDto(patient));
        }
        return dtoList;
    }
    
    public PatientDto patientDetails(String opdSearchField){
        Patient patient = crudApi.getEm().createQuery("SELECT e FROM Patient e WHERE e.opdNumber=:opdSearchField", Patient.class)
                    .setParameter("opdSearchField", opdSearchField)
                    .getSingleResult();
        
        return patientMapper.toDto(patient);
    }
    
    public boolean delete(String patientId){
        Patient patient = appService.getPatient(patientId);
        if(patient != null){
            return crudApi.delete(patient);
        }
        return false;
    }

    public PatientVitalDto save(String userAccountId, PatientVitalDto dto, String patientId)
    {
      if (dto.getId() != null){
            PatientVital patientVital = crudApi.find(PatientVital.class, dto.getId());
            if (patientVital == null){
                throw new DataNotFoundException("Patient Vital with ID: "+ dto.getId() +" Not Found");
            }
        }  
      PatientVital vital = patientMapper.toEntity(dto);
      vital.setUserAccount(appService.getUser(userAccountId));
      vital.setPatient(appService.getPatient(patientId));
      if(crudApi.save(vital) != null){
          return patientMapper.toDto(vital);
      }
      return null;
    }

    public List<PatientVitalDto> patientVitalList(String patientId){
        List<PatientVitalDto> dtoList = new LinkedList<>();
        Patient patient = appService.getPatient(patientId);
        if(patient != null){
            List<PatientVital> pvList = crudApi.getEm().createQuery("SELECT e FROM PatientVital e WHERE e.patient=:patient", PatientVital.class)
                        .setParameter("patient", patient)
                        .getResultList();

            for (PatientVital pv : pvList)
            {
                dtoList.add(patientMapper.toDto(pv));
            }
        }
        return dtoList; 
    }

    public PatientVitalDto findById(String patientId, String vitalId)
    {
         PatientVital patientVital = findVital(patientId, vitalId);
         if(patientVital != null){
             return patientMapper.toDto(patientVital);
         }
         return null;
    }

    private PatientVital findVital(String patientId, String vitalId){
        Patient patient = appService.getPatient(patientId);
        if(patient != null)
            return crudApi.getEm().createQuery("SELECT e FROM PatientVital e WHERE e.patient=:patient AND e.id=:vitalId", PatientVital.class)
                    .setParameter("patient", patient)
                    .setParameter("vitalId", vitalId)
                    .getSingleResult();
        else
           return null;
    }
    public boolean delete(String patientId, String vitalId)
    {
       PatientVital patient = findVital(patientId, vitalId);
        if(patient != null){
            return crudApi.delete(patient);
        }
        return false;
    }

    // Assigned DR
    public AssignDrDto save(String userAccountId, AssignDrDto dto, String patientId)
    {
       if (dto.getId() != null){
            AssignDr assignDr = crudApi.find(AssignDr.class, dto.getId());
            if (assignDr == null){
                throw new DataNotFoundException("Assign Dr with ID: "+ dto.getId() +" Not Found");
            }
        }  
      AssignDr assignDr = patientMapper.toEntity(dto);
        System.out.println("Data: "+SystemUtils.KJson().toJson(dto));
      assignDr.setUserAccount(appService.getUser(userAccountId));
      assignDr.setPatient(appService.getPatient(patientId));
      if(crudApi.save(assignDr) != null){
          return patientMapper.toDto(assignDr);
      }
      return null; 
    }
    
    public List<AssignDrDto> assingDrList(String patientId){
    List<AssignDrDto> dtoList = new LinkedList<>();
    Patient patient = appService.getPatient(patientId);
    if(patient != null){
        List<AssignDr> pvList = crudApi.getEm().createQuery("SELECT e FROM AssignDr e WHERE e.patient=:patient", AssignDr.class)
                    .setParameter("patient", patient)
                    .getResultList();

        for (AssignDr assignDr : pvList)
        {
            dtoList.add(patientMapper.toDto(assignDr));
        }
    }
    return dtoList; 
    }
    
    public List<AssignDrDto> loadPatientsForDr(String doctorId){
    List<AssignDrDto> dtoList = new LinkedList<>();
        UserAccount userAccount = appService.getUser(doctorId);
    if(userAccount != null){
        List<AssignDr> pvList = crudApi.getEm().createQuery("SELECT e FROM AssignDr e WHERE e.userAccount=:userAccount", AssignDr.class)
                    .setParameter("userAccount", userAccount)
                    .getResultList();
        for (AssignDr assignDr : pvList){
            dtoList.add(patientMapper.toDto(assignDr));
        }
    }
    return dtoList; 
    }
    
    private AssignDr findAssignDr(String patientId, String assignedDrId){
        Patient patient = appService.getPatient(patientId);
        if(patient != null)
            return crudApi.getEm().createQuery("SELECT e FROM AssignDr e WHERE e.patient=:patient AND e.id=:assignedDrId", AssignDr.class)
                    .setParameter("patient", patient)
                    .setParameter("assignedDrId", assignedDrId)
                    .getSingleResult();
        else
            return null;
    }
    
    public AssignDrDto finByAssignedDrId(String patientId, String assignedDrId)
    {
         AssignDr assignDr = findAssignDr(patientId, assignedDrId);
         if(assignDr != null){
             return patientMapper.toDto(assignDr);
         }
         return null;
    }
    
    public boolean deleteAsDr(String patientId, String assignedDrId)
    {
       AssignDr assignDr = findAssignDr(patientId, assignedDrId);
        if(assignDr != null)
            return crudApi.delete(assignDr);
        return false;
    }

    // Prescription
    public PrescriptionDto save(String userAccountId,String employeeId, PrescriptionDto dto, String patientId){
      if (dto.getId() != null){
            Prescription prescription = crudApi.find(Prescription.class, dto.getId());
            if (prescription == null){
                throw new DataNotFoundException("Prescription with ID: "+ dto.getId() +" Not Found");
            }
        }  
      Prescription prescription = patientMapper.toEntity(dto);
      prescription.setUserAccount(appService.getUser(userAccountId));
      prescription.setPatient(appService.getPatient(patientId));
      prescription.setEmployee(appService.getEmployee(employeeId));
      if(crudApi.save(prescription) != null){
          return patientMapper.toDto(prescription);
      }
      return null;   
    }

    public List<PrescriptionDto> prescriptionList(String patientId, String employeeId)
    {
        List<PrescriptionDto> dtoList = new LinkedList<>();
        Patient patient = appService.getPatient(patientId);
        Employee employee = appService.getEmployee(employeeId);
        if(patient != null && employee != null){
            List<Prescription> prescriptionsList = crudApi.getEm().createQuery("SELECT e FROM Prescription e WHERE e.patient=:patient AND e.employee=:employee", Prescription.class)
                        .setParameter("patient", patient)
                        .setParameter("employee", employee )
                        .getResultList();

            for (Prescription prescription : prescriptionsList){
                dtoList.add(patientMapper.toDto(prescription));
            }
        }
        return dtoList; 
    }

    private Prescription findByPrescriptionId(String patientId, String prescriptionId)
    {
        Patient patient = appService.getPatient(patientId);
        if(patient != null)
            return crudApi.getEm().createQuery("SELECT e FROM Prescription e WHERE e.patient=:patient AND e.id=:prescriptionId", Prescription.class)
                    .setParameter("patient", patient)
                    .setParameter("prescriptionId", prescriptionId)
                    .getResultStream().findFirst().orElse(null);
        else
            return null;
    }
    
    public PrescriptionDto findByPrescription(String patientId, String prescriptionId)
    {
         Prescription prescription = findByPrescriptionId(patientId, prescriptionId);
         if(prescription != null)
             return patientMapper.toDto(prescription);
         return null;
    }

    public boolean deletePrescription(String patientId, String prescriptionId)
    {
       Prescription prescription = findByPrescriptionId(patientId, prescriptionId);
        if(prescription != null)
            return crudApi.delete(prescription);
        return false;
    }
    
    
    public DrReportDto save(String userAccountId, String employeeId, DrReportDto dto, String patientId){
      if (dto.getId() != null){
            DrReport drReport = crudApi.find(DrReport.class, dto.getId());
            if (drReport == null){
                throw new DataNotFoundException("DrReport with ID: "+ dto.getId() +" Not Found");
            }
        }  
      DrReport drReport = patientMapper.toEntity(dto);
      drReport.setUserAccount(appService.getUser(userAccountId));
      drReport.setPatient(appService.getPatient(patientId));
      drReport.setEmployee(appService.getEmployee(employeeId));
      if(crudApi.save(drReport) != null){
          return patientMapper.toDto(drReport);
      }
      return null;   
    }

    public List<DrReportDto> drReportList(String patientId,String complainId)
    {
        List<DrReportDto> dtoList = new LinkedList<>();
        Patient patient = appService.getPatient(patientId);
        Complain complain = crudApi.find(Complain.class, complainId);
        if(patient != null && complain != null){
            List<DrReport> drReportList = crudApi.getEm().createQuery("SELECT e FROM DrReport e WHERE e.patient=:patient AND e.complain=:complain", DrReport.class)
                        .setParameter("patient", patient)
                        .setParameter("complain", complain)
                        .getResultList();

            for (DrReport drReport : drReportList){
                dtoList.add(patientMapper.toDto(drReport));
            }
        }
        return dtoList; 
    }

    private DrReport findByDrReportId(String patientId, String drReportId)
    {
        Patient patient = appService.getPatient(patientId);
        if(patient != null)
            return crudApi.getEm().createQuery("SELECT e FROM DrReport e WHERE e.patient=:patient AND e.id=:drReportId", DrReport.class)
                    .setParameter("patient", patient)
                    .setParameter("drReportId", drReportId)
                    .getSingleResult();
        else
            return null;
    }
    
    public DrReportDto findByDrReport(String patientId, String drReportId)
    {
         DrReport drReport = findByDrReportId(patientId, drReportId);
         if(drReport != null)
             return patientMapper.toDto(drReport);
         return null;
    }

    public boolean deleteDrReport(String patientId, String drReportId){
       DrReport drReport = findByDrReportId(patientId, drReportId);
        if(drReport != null)
            return crudApi.delete(drReport);
        return false;
    }
}
