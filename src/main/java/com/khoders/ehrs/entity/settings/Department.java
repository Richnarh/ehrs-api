/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.settings;

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
@Table(name = "department")
public class Department extends UserAccountRecord
{
    @Column(name = "department_code")
    private String departmentCode;
    
    @Column(name = "department_name")
    private String departmentName;
    
    @JoinColumn(name = "hod", referencedColumnName = "id")
    @ManyToOne
    private Employee hod;

    public String getDepartmentCode()
    {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public Employee getHod()
    {
        return hod;
    }

    public void setHod(Employee hod)
    {
        this.hod = hod;
    }

    @Override
    public String toString()
    {
        return departmentName;
    }
    
}
