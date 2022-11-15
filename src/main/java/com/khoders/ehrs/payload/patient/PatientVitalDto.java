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
public class PatientVitalDto extends BaseDto
{
    private String patientName;
    private String patientId;
    private String vitalDate;
    private String bp;
    private String temp;
    private String pulse;
    private String spTwo;
    private String weight;
    private String comment; 
    private String physicianId; 
    private String physician; 
    private String source; 

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

    public String getVitalDate()
    {
        return vitalDate;
    }

    public void setVitalDate(String vitalDate)
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

    public String getPhysicianId()
    {
        return physicianId;
    }

    public void setPhysicianId(String physicianId)
    {
        this.physicianId = physicianId;
    }

    public String getPhysician()
    {
        return physician;
    }

    public void setPhysician(String physician)
    {
        this.physician = physician;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }
    
}
