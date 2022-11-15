/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload;

/**
 *
 * @author richa
 */
public class LeaveRequestDto extends BaseDto
{
    private String leaveTypeName;
    private String leaveTypeId;
    private String departmentName;
    private String departmentId;
    private String periodFrom;
    private String periodTo;
    private String comment;
    private int totalLeaveDays;
    private String leaveStatus;  

    public String getLeaveTypeName()
    {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName)
    {
        this.leaveTypeName = leaveTypeName;
    }

    public String getLeaveTypeId()
    {
        return leaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId)
    {
        this.leaveTypeId = leaveTypeId;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getPeriodTo()
    {
        return periodTo;
    }

    public void setPeriodTo(String periodTo)
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

    public String getLeaveStatus()
    {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus)
    {
        this.leaveStatus = leaveStatus;
    }

    public String getPeriodFrom()
    {
        return periodFrom;
    }

    public void setPeriodFrom(String periodFrom)
    {
        this.periodFrom = periodFrom;
    }
  
}
