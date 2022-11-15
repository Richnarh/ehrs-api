/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.leave;

import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.entity.Ref;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.administration.Employee;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "special_leave")
public class SpecialLeave extends UserAccountRecord
{
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @ManyToOne
    private Employee employee;
    
    @Column(name = "max_no_of_days")
    private int maxNumberOfDays;
    
    @JoinColumn(name = "leave_type", referencedColumnName = "id")
    @ManyToOne
    private LeaveType leaveType;
    
    @Column(name = "purpose")
    @Lob
    private String purpose;
    
    @Column(name="description")
    private String description;

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public int getMaxNumberOfDays()
    {
        return maxNumberOfDays;
    }

    public void setMaxNumberOfDays(int maxNumberOfDays)
    {
        this.maxNumberOfDays = maxNumberOfDays;
    }

    public LeaveType getLeaveType()
    {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType)
    {
        this.leaveType = leaveType;
    }

    public String getPurpose()
    {
        return purpose;
    }

    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
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
