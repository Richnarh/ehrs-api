/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.PatientDatedRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richard
 */
@Entity
@Table(name = "patient_drug")
public class PatientDrug extends PatientDatedRecord
{
    @JoinColumn(name = "dr_report", referencedColumnName = "id")
    @ManyToOne
    private DrReport drReport;
    
    @Column(name = "product_name")
    private String productName;
    
    
    @Column(name = "note")
    @Lob
    private String note;

    public DrReport getDrReport()
    {
        return drReport;
    }

    public void setDrReport(DrReport drReport)
    {
        this.drReport = drReport;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }
    
    
}
