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
public class UserAccountDto extends BaseDto
{
    private String emailAddress;
    private String phoneNumber;
    private String title;
    private String jobRoleName;
    private String jobRoleId;
    private String password;

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getJobRoleName()
    {
        return jobRoleName;
    }

    public void setJobRoleName(String jobRoleName)
    {
        this.jobRoleName = jobRoleName;
    }

    public String getJobRoleId()
    {
        return jobRoleId;
    }

    public void setJobRoleId(String jobRoleId)
    {
        this.jobRoleId = jobRoleId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
}
