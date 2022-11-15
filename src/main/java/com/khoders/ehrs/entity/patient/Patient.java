/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.patient;

import com.khoders.ehrs.commons.PatientCategory;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.resource.enums.Gender;
import com.khoders.resource.enums.IdType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "patient")
public class Patient  extends UserAccountRecord
{
    @Column(name = "full_name")
    private String fullname;
    
    @Column(name = "opd_number")
    private String opdNumber;
    
    @Column(name = "occupation")
    private String occupation;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "patient_category")
    @Enumerated(EnumType.STRING)
    private PatientCategory patientCategory;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "id_type")
    @Enumerated(EnumType.STRING)
    private IdType idType;
    
    @Column(name = "id_number")
    private String idNumber;
    
    @Column(name = "address")
    private String address;

    public String getOpdNumber()
    {
        return opdNumber;
    }

    public void setOpdNumber(String opdNumber)
    {
        this.opdNumber = opdNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
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

    public PatientCategory getPatientCategory()
    {
        return patientCategory;
    }

    public void setPatientCategory(PatientCategory patientCategory)
    {
        this.patientCategory = patientCategory;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }
    
}
