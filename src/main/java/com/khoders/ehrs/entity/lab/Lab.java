/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.lab;

import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.administration.Employee;
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
@Table(name = "lab")
public class Lab extends UserAccountRecord
{
    @Column(name = "unit_no")
    private String unitNo;
    
    @Column(name = "unit_name")
    private String unitName;
    
    @JoinColumn(name = "unit_head")
    @ManyToOne
    private Employee unitHead;

    public String getUnitNo()
    {
        return unitNo;
    }

    public void setUnitNo(String unitNo)
    {
        this.unitNo = unitNo;
    }
    
    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public Employee getUnitHead()
    {
        return unitHead;
    }

    public void setUnitHead(Employee unitHead)
    {
        this.unitHead = unitHead;
    }
    
}
