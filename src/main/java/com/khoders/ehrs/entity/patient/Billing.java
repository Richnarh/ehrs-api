/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.PatientDatedRecord;
import com.khoders.ehrs.entity.lab.LabResult;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "billing")
public class Billing extends PatientDatedRecord
{
    @Column(name = "bill_no")
    private String billNo;
   
    @JoinColumn(name = "admission_bill", referencedColumnName = "id")
    @ManyToOne
    private PatientAdmission admissionBill;
    
    @JoinColumn(name = "lab_bill", referencedColumnName = "id")
    @ManyToOne
    private LabResult labBill;
        
    @JoinColumn(name = "prescription_bill", referencedColumnName = "id")
    @ManyToOne
    private Prescription prescriptionBill;
    
    @Column(name = "other_bill")
    private double otherBill;

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public PatientAdmission getAdmissionBill()
    {
        return admissionBill;
    }

    public void setAdmissionBill(PatientAdmission admissionBill)
    {
        this.admissionBill = admissionBill;
    }

    public LabResult getLabBill()
    {
        return labBill;
    }

    public void setLabBill(LabResult labBill)
    {
        this.labBill = labBill;
    }

    public Prescription getPrescriptionBill()
    {
        return prescriptionBill;
    }

    public void setPrescriptionBill(Prescription prescriptionBill)
    {
        this.prescriptionBill = prescriptionBill;
    }

    public double getOtherBill()
    {
        return otherBill;
    }

    public void setOtherBill(double otherBill)
    {
        this.otherBill = otherBill;
    }
    
}
