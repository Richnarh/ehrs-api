/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.PatientDatedRecord;
import com.khoders.ehrs.entity.lab.Lab;
import java.time.LocalDate;
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
@Table(name = "patient_admission")
public class PatientAdmission extends PatientDatedRecord
{
    @JoinColumn(name = "lab", referencedColumnName = "id")
    @ManyToOne
    private Lab lab;
    
    @Column(name = "admission_date")
    private LocalDate admissionDate;
    
    @Column(name = "no_of_days")
    private double noOfDays;
    
    @Column(name = "price")
    private double price;
        
    @JoinColumn(name = "ward")
    @ManyToOne
    private Ward ward;

    public Lab getLab()
    {
        return lab;
    }

    public void setLab(Lab lab)
    {
        this.lab = lab;
    }

    public LocalDate getAdmissionDate()
    {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate)
    {
        this.admissionDate = admissionDate;
    }

    public Ward getWard()
    {
        return ward;
    }

    public void setWard(Ward ward)
    {
        this.ward = ward;
    }

    public double getNoOfDays()
    {
        return noOfDays;
    }

    public void setNoOfDays(double noOfDays)
    {
        this.noOfDays = noOfDays;
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
