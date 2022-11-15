/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.commons.AssignPatient;
import com.khoders.ehrs.entity.PatientDatedRecord;
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
@Table(name = "dr_report")
public class DrReport extends PatientDatedRecord
{
    @Column(name = "comment")
    @Lob
    private String comment;
    
    @JoinColumn(name = "complain", referencedColumnName = "id")
    @ManyToOne
    Complain complain;   
    
    @Column(name = "patient_assign")
    @Enumerated(EnumType.STRING)
    private AssignPatient assignPatient;

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public AssignPatient getAssignPatient()
    {
        return assignPatient;
    }

    public void setAssignPatient(AssignPatient assignPatient)
    {
        this.assignPatient = assignPatient;
    }

    public Complain getComplain()
    {
        return complain;
    }

    public void setComplain(Complain complain)
    {
        this.complain = complain;
    }
    
}
