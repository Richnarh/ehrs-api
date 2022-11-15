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
public class PatientAdmissionDto extends BaseDto
{
    private String patientName;
    private String patientId;
    private String labId;
    private String labName;
    private String admissionDate;
    private double noOfDays;
    private double price;
    private String roomName;
    private String roomId;

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

    public String getLabId()
    {
        return labId;
    }

    public void setLabId(String labId)
    {
        this.labId = labId;
    }

    public String getLabName()
    {
        return labName;
    }

    public void setLabName(String labName)
    {
        this.labName = labName;
    }

    public String getAdmissionDate()
    {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate)
    {
        this.admissionDate = admissionDate;
    }

    public double getNoOfDays()
    {
        return noOfDays;
    }

    public void setNoOfDays(double noOfDays)
    {
        this.noOfDays = noOfDays;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public String getRoomId()
    {
        return roomId;
    }

    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
    
}
