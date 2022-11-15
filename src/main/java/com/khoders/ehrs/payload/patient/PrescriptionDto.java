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
public class PrescriptionDto extends BaseDto
{
    private String drReport;
    private String drReportId;
    private String patient;
    private String patientId;
    private String inventory;
    private String inventoryId;
    private String frequency;
    private String frequencyId;
    private String dose;
    private String notes;
    private double price;

    public String getDrReport()
    {
        return drReport;
    }

    public void setDrReport(String drReport)
    {
        this.drReport = drReport;
    }

    public String getDrReportId()
    {
        return drReportId;
    }

    public void setDrReportId(String drReportId)
    {
        this.drReportId = drReportId;
    }

    public String getInventory()
    {
        return inventory;
    }

    public void setInventory(String inventory)
    {
        this.inventory = inventory;
    }

    public String getInventoryId()
    {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId)
    {
        this.inventoryId = inventoryId;
    }

    public String getFrequency()
    {
        return frequency;
    }

    public void setFrequency(String frequency)
    {
        this.frequency = frequency;
    }

    public String getFrequencyId()
    {
        return frequencyId;
    }

    public void setFrequencyId(String frequencyId)
    {
        this.frequencyId = frequencyId;
    }

    public String getDose()
    {
        return dose;
    }

    public void setDose(String dose)
    {
        this.dose = dose;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getPatient()
    {
        return patient;
    }

    public void setPatient(String patient)
    {
        this.patient = patient;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public void setPatientId(String patientId)
    {
        this.patientId = patientId;
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
