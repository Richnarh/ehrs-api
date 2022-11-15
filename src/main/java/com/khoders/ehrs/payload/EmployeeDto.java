package com.khoders.ehrs.payload;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author richa
 */
public class EmployeeDto extends BaseDto
{
    private String title;
    private String jobRole;
    private String jobRoleId;
    private String firstname;
    private String middlename;
    private String lastname;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    private String idType;
    private String idNumber;
    private String ssnitNo;
    private String departmentName;
    private String departmentId;
    private boolean canLogin;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getJobRole()
    {
        return jobRole;
    }

    public void setJobRole(String jobRole)
    {
        this.jobRole = jobRole;
    }

    public String getJobRoleId()
    {
        return jobRoleId;
    }

    public void setJobRoleId(String jobRoleId)
    {
        this.jobRoleId = jobRoleId;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getMiddlename()
    {
        return middlename;
    }

    public void setMiddlename(String middlename)
    {
        this.middlename = middlename;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getIdType()
    {
        return idType;
    }

    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    public String getIdNumber()
    {
        return idNumber;
    }

    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getSsnitNo()
    {
        return ssnitNo;
    }

    public void setSsnitNo(String ssnitNo)
    {
        this.ssnitNo = ssnitNo;
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

    public boolean isCanLogin()
    {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin)
    {
        this.canLogin = canLogin;
    }
    
}
