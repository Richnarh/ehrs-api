/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.administration;

import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.resource.enums.AccessLevel;
import com.khoders.resource.enums.IdType;
import com.khoders.resource.enums.Status;
import com.khoders.resource.enums.Title;
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
@Table(name = "employee")
public class Employee extends UserAccountRecord
{
    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private Title title;
    
    @JoinColumn(name = "job_role", referencedColumnName = "id")
    @ManyToOne
    private JobRole jobRole;
    
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne
    private Department department;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "middlename")
    private String middlename;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "id_type")
    @Enumerated(EnumType.STRING)
    private IdType idType;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "access_level")
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;
    
    @Column(name = "id_number")
    private String idNumber;
    
    @Column(name = "ssnit_no")
    private String ssnitNo;
    
    @Column(name = "can_login")
    private boolean canLogin;
    
    @Column(name = "fullname")
    private String fullname = firstname +" "+lastname;

    public Title getTitle()
    {
        return title;
    }

    public void setTitle(Title title)
    {
        this.title = title;
    }

    public JobRole getJobRole()
    {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole)
    {
        this.jobRole = jobRole;
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

    public IdType getIdType()
    {
        return idType;
    }

    public void setIdType(IdType idType)
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

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public AccessLevel getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    public boolean isCanLogin()
    {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin)
    {
        this.canLogin = canLogin;
    }

    @Override
    public String toString()
    {
        return lastname +" "+phoneNumber;
    }
}
