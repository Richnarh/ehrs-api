/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload.patient;

import com.khoders.ehrs.payload.BaseDto;

/**
 *
 * @author richa
 */
public class AssignDrDto extends BaseDto
{
    private String doctor;
    private String doctorId;
    private String patientName;
    private String patientId;
    private String note;
    private String opdNumber;
    private String assignDrStatus;
    private String assignTime;

    public String getDoctor()
    {
        return doctor;
    }

    public void setDoctor(String doctor)
    {
        this.doctor = doctor;
    }

    public String getDoctorId()
    {
        return doctorId;
    }

    public void setDoctorId(String doctorId)
    {
        this.doctorId = doctorId;
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

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getAssignDrStatus()
    {
        return assignDrStatus;
    }

    public void setAssignDrStatus(String assignDrStatus)
    {
        this.assignDrStatus = assignDrStatus;
    }

    public String getOpdNumber()
    {
        return opdNumber;
    }

    public void setOpdNumber(String opdNumber)
    {
        this.opdNumber = opdNumber;
    }

    public String getAssignTime()
    {
        return assignTime;
    }

    public void setAssignTime(String assignTime)
    {
        this.assignTime = assignTime;
    }

}
