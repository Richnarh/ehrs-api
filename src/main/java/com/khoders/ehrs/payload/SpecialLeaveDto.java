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
public class SpecialLeaveDto extends BaseDto
{
    private String employeeName;
    private String employeeId;
    private int maxNumberOfDays;
    private String leaveTypeName;
    private String leaveTypeId;
    private String purpose;
    private String description;  

    public int getMaxNumberOfDays()
    {
        return maxNumberOfDays;
    }

    public void setMaxNumberOfDays(int maxNumberOfDays)
    {
        this.maxNumberOfDays = maxNumberOfDays;
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

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

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
    
}
