/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.commons.Source;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.administration.Employee;
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
@Table(name = "patient_report")
public class PatientReport extends UserAccountRecord
{
    @JoinColumn(name = "patient_valital", referencedColumnName = "id")
    @ManyToOne
    private PatientVital patientVital;
    
    @JoinColumn(name = "nurse", referencedColumnName = "id")
    @ManyToOne
    private Employee nurse;
    
    @JoinColumn(name = "ward", referencedColumnName = "id")
    @ManyToOne
    private Ward ward;
    
    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private Source source;

    public PatientVital getPatientVital()
    {
        return patientVital;
    }

    public void setPatientVital(PatientVital patientVital)
    {
        this.patientVital = patientVital;
    }

    public Employee getNurse()
    {
        return nurse;
    }

    public void setNurse(Employee nurse)
    {
        this.nurse = nurse;
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
