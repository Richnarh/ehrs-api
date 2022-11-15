/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.entity.settings.WardType;
import com.khoders.ehrs.entity.UserAccountRecord;
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
@Table(name = "ward")
public class Ward extends UserAccountRecord
{
   @Column(name = "ward_no")
   private String wardNo;
   
   @JoinColumn(name = "ward_type", referencedColumnName = "id")
   @ManyToOne
   private WardType wardType;

   @Column(name = "description")
   @Lob
   private String description;
   
    public String getWardNo()
    {
        return wardNo;
    }

    public void setWardNo(String wardNo)
    {
        this.wardNo = wardNo;
    }

    public WardType getWardType()
    {
        return wardType;
    }

    public void setWardType(WardType wardType)
    {
        this.wardType = wardType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
   
}
