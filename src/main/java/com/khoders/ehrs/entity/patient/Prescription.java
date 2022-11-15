/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.PatientDatedRecord;
import com.khoders.ehrs.entity.pharmacy.Inventory;
import com.khoders.ehrs.entity.settings.Frequency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "prescription")
public class Prescription extends PatientDatedRecord
{
    @JoinColumn(name = "dr_report", referencedColumnName = "id")
    @ManyToOne
    private DrReport drReport;
    
    @JoinColumn(name = "inventory", referencedColumnName = "id")
    @ManyToOne
    private Inventory inventory;
    
    @JoinColumn(name = "frequency", referencedColumnName = "id")
    @ManyToOne
    private Frequency frequency;
    
    @Column(name = "price")
    private double price;
        
    @Column(name = "dose")
    private String dose;
    
    @Column(name = "notes")
    @Lob
    private String notes;

    public DrReport getDrReport()
    {
        return drReport;
    }

    public void setDrReport(DrReport drReport)
    {
        this.drReport = drReport;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public Frequency getFrequency()
    {
        return frequency;
    }

    public void setFrequency(Frequency frequency)
    {
        this.frequency = frequency;
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

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
    
}
