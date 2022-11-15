/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.PatientDatedRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "complains")
public class Complain extends PatientDatedRecord{
    @Column(name = "complains")
    @Lob
    private String complains;

    public String getComplains()
    {
        return complains;
    }

    public void setComplains(String complains)
    {
        this.complains = complains;
    }
    
}
