/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.commons.Source;
import com.khoders.ehrs.entity.PatientDatedRecord;
import com.khoders.ehrs.entity.administration.Employee;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "patient_vital")
public class PatientVital extends PatientDatedRecord
{
    @Column(name = "vital_date")
    private LocalDateTime vitalDate;
    
    @Column(name = "bp")
    private String bp;
    
    @Column(name = "temp")
    private String temp;
    
    @Column(name = "pulse")
    private String pulse;
    
    @Column(name = "sp_two")
    private String spTwo;
    
    @Column(name = "weight")
    private String weight;
    
    @Column(name = "comment")
    private String comment;
    
    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private Source source;
    
    @JoinColumn(name = "physician", referencedColumnName = "id")
    @ManyToOne
    private Employee physician;
    
    public LocalDateTime getVitalDate()
    {
        return vitalDate;
    }

    public void setVitalDate(LocalDateTime vitalDate)
    {
        this.vitalDate = vitalDate;
    }

    public String getBp()
    {
        return bp;
    }

    public void setBp(String bp)
    {
        this.bp = bp;
    }

    public String getTemp()
    {
        return temp;
    }

    public void setTemp(String temp)
    {
        this.temp = temp;
    }

    public String getPulse()
    {
        return pulse;
    }

    public void setPulse(String pulse)
    {
        this.pulse = pulse;
    }

    public String getSpTwo()
    {
        return spTwo;
    }

    public void setSpTwo(String spTwo)
    {
        this.spTwo = spTwo;
    }
    
    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Employee getPhysician()
    {
        return physician;
    }

    public void setPhysician(Employee physician)
    {
        this.physician = physician;
    }

    public Source getSource()
    {
        return source;
    }

    public void setSource(Source source)
    {
        this.source = source;
    }
    
}
