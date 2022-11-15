/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload.patient;

import com.khoders.ehrs.payload.BaseDto;

/**
 *
 * @author richard
 */
public class BillingDto extends BaseDto
{
    private String billNo;
    private String patientName;
    private String patientId;
    private double admissionBill;
    private String admissionBillId;
    private double labBill;
    private String labBillId;
    private double prescriptionBill;
    private String prescriptionBillId;
    private double otherBill;

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public void setPatientId(String patientId)
    {
        this.patientId = patientId;
    }

    public double getAdmissionBill()
    {
        return admissionBill;
    }

    public void setAdmissionBill(double admissionBill)
    {
        this.admissionBill = admissionBill;
    }

    public String getAdmissionBillId()
    {
        return admissionBillId;
    }

    public void setAdmissionBillId(String admissionBillId)
    {
        this.admissionBillId = admissionBillId;
    }

    public double getLabBill()
    {
        return labBill;
    }

    public void setLabBill(double labBill)
    {
        this.labBill = labBill;
    }

    public String getLabBillId()
    {
        return labBillId;
    }

    public void setLabBillId(String labBillId)
    {
        this.labBillId = labBillId;
    }

    public double getPrescriptionBill()
    {
        return prescriptionBill;
    }

    public void setPrescriptionBill(double prescriptionBill)
    {
        this.prescriptionBill = prescriptionBill;
    }

    public String getPrescriptionBillId()
    {
        return prescriptionBillId;
    }

    public void setPrescriptionBillId(String prescriptionBillId)
    {
        this.prescriptionBillId = prescriptionBillId;
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
