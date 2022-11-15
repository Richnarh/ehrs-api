/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload;

import java.util.List;

/**
 *
 * @author richa
 */
public class AuthResponse extends BaseDto{
    private String emailAddress;
    private String sessionId;
    private String employee;
    private String employeeId;
    private List<AppModuleDto> appModuleList;

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getEmployee()
    {
        return employee;
    }

    public void setEmployee(String employee)
    {
        this.employee = employee;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public List<AppModuleDto> getAppModuleList() {
        return appModuleList;
    }

    public void setAppModuleList(List<AppModuleDto> appModuleList) {
        this.appModuleList = appModuleList;
    }
    
}
