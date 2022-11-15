/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.leave;

import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.commons.LeaveStatus;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.settings.Department;
import java.time.LocalDate;
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
@Table(name = "leave_requests")
public class LeaveRequest extends UserAccountRecord
{
    @ManyToOne
    @JoinColumn(name = "leave_type")
    private LeaveType leaveType;

    @JoinColumn(name = "employee_dept")
    @ManyToOne
    private Department department;
             
    @Column(name="leave_from")
    private LocalDate periodFrom;
             
    @Column(name="leave_to")
    private LocalDate periodTo;
    
    @Column(name="comment")
    private String comment;
        
    @Column(name="total_leave_days")
    private int totalLeaveDays;
    
    @Column(name="leave_status")
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;  

    public LeaveType getLeaveType()
    {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType)
    {
        this.leaveType = leaveType;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public LocalDate getPeriodTo()
    {
        return periodTo;
    }

    public void setPeriodTo(LocalDate periodTo)
    {
        this.periodTo = periodTo;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public int getTotalLeaveDays()
    {
        return totalLeaveDays;
    }

    public void setTotalLeaveDays(int totalLeaveDays)
    {
        this.totalLeaveDays = totalLeaveDays;
    }

    public LeaveStatus getLeaveStatus()
    {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus)
    {
        this.leaveStatus = leaveStatus;
    }

    public LocalDate getPeriodFrom()
    {
        return periodFrom;
    }

    public void setPeriodFrom(LocalDate periodFrom)
    {
        this.periodFrom = periodFrom;
    }
    
}
