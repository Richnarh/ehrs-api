/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.administration;

import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.entity.Ref;
import com.khoders.resource.enums.AccessLevel;
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
 * @author richard
 */
@Entity
@Table(name = "user_account")
public class UserAccount extends Ref
{
    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private Title title;
    
    @Column(name = "access_level")
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;
    
    @JoinColumn(name = "job_role", referencedColumnName = "id")
    @ManyToOne
    private JobRole jobRole;
        
    @Column(name = "password")
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AccessLevel getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString()
    {
        return emailAddress;
    }
    
}
