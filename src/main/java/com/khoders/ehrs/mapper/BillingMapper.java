/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.lab.LabResult;
import com.khoders.ehrs.entity.patient.Billing;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.patient.PatientAdmission;
import com.khoders.ehrs.entity.patient.Prescription;
import com.khoders.ehrs.payload.patient.BillingDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import com.khoders.resource.utilities.SystemUtils;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class BillingMapper
{
    @Inject private CrudApi crudApi;
    
    public Billing toEntity(BillingDto dto){
        Billing billing = new Billing();
        if (dto.getId() != null)
        {
            billing.setId(dto.getId());
        }
        if(dto.getAdmissionBillId() == null)
            throw new DataNotFoundException("Please Specify Admission Id");
        if(dto.getLabBillId() == null)
            throw new DataNotFoundException("Please Specify Lab Id");
        if(dto.getPrescriptionBillId() == null)
            throw new DataNotFoundException("Please Specify Lab Id");
            
        PatientAdmission admission = crudApi.find(PatientAdmission.class, dto.getAdmissionBillId());
        LabResult labResult = crudApi.find(LabResult.class, dto.getLabBillId());
        Prescription prescription = crudApi.find(Prescription.class, dto.getPrescriptionBillId());
        
        if(admission != null)
            billing.setAdmissionBill(admission);
        if(labResult != null)
            billing.setLabBill(labResult);
        if(prescription != null)
            billing.setPrescriptionBill(prescription);
        
        billing.setBillNo(dto.getBillNo() == null ? SystemUtils.generateRefNo() : dto.getBillNo());
        billing.setOtherBill(dto.getOtherBill());
        billing.genCode();
            
        return billing;
    }
    
    public BillingDto toDto(Billing billing){
        BillingDto dto = new BillingDto();
        if(billing.getId() == null) return null;
        dto.setId(billing.getId());
        
        if(billing.getPatient() != null){
            dto.setPatientId(billing.getPatient().getId());
            dto.setPatientName(billing.getPatient().getFullname());
        }
        dto.setRefNo(billing.getRefNo());
        dto.setBillNo(billing.getBillNo());
        
        dto.setAdmissionBill(billing.getAdmissionBill() != null ? billing.getAdmissionBill().getPrice() : 0.0);
        dto.setAdmissionBillId(billing.getAdmissionBill() != null ? billing.getAdmissionBill().getId() : "-");
        
        dto.setLabBill(billing.getLabBill() != null ? billing.getLabBill().getPrice() : 0.0);
        dto.setLabBillId(billing.getLabBill() != null ? billing.getLabBill().getId() : "-");
        
        dto.setPrescriptionBill(billing.getPrescriptionBill() != null ? billing.getPrescriptionBill().getPrice() : 0.0);
        dto.setPrescriptionBillId(billing.getPrescriptionBill() != null ? billing.getPrescriptionBill().getId() : "-");
        dto.setOtherBill(billing.getOtherBill());
        
        dto.setPatientId(billing.getPatient() != null ? billing.getPatient().getId() : "-");
        dto.setPatientName(billing.getPatient() != null ? billing.getPatient().getFullname() : "-");
        dto.setValueDate(DateUtil.parseLocalDateString(billing.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
