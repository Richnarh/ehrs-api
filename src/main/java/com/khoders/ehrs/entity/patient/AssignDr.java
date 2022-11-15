/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.commons.AssignDrStatus;
import com.khoders.ehrs.entity.PatientDatedRecord;
import com.khoders.ehrs.entity.administration.Employee;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "assign_dr")
public class AssignDr extends PatientDatedRecord
{
    @JoinColumn(name = "doctor", referencedColumnName = "id")
    @ManyToOne
    private Employee doctor;
    
    @Column(name = "note")
    @Lob
    private String note;
    
    @Column(name = "assign_dr_status")
    @Enumerated(EnumType.STRING)
    private AssignDrStatus assignDrStatus;

    public Employee getDoctor()
    {
        return doctor;
    }

    public void setDoctor(Employee doctor)
    {
        this.doctor = doctor;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public AssignDrStatus getAssignDrStatus()
    {
        return assignDrStatus;
    }

    public void setAssignDrStatus(AssignDrStatus assignDrStatus)
    {
        this.assignDrStatus = assignDrStatus;
    }
    
}
