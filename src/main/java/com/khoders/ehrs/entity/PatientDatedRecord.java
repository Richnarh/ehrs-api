/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.patient.Patient;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author richa
 */
@MappedSuperclass
public class PatientDatedRecord extends UserAccountRecord
{
    @JoinColumn(name = "patient", referencedColumnName = "id")
    @ManyToOne
    Patient patient;   
    
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @ManyToOne
    Employee employee;   

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
    
}
