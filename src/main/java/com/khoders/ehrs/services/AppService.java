/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
@Stateless
public class AppService
{
    @Inject private CrudApi crudApi;
    
    public UserAccount getUser(String userAccountId){
        UserAccount user =  crudApi.find(UserAccount.class, userAccountId);
        if(user == null){
           throw new DataNotFoundException("User with the ID: "+userAccountId +" cannot be found!");
        }
        return user;
    }
    public Patient getPatient(String patientId){
        Patient patient =  crudApi.find(Patient.class, patientId);
        if(patient == null){
           throw new DataNotFoundException("Patient with the ID: "+patientId +" cannot be found!");
        }
        return patient;
    }
    public Employee getEmployee(String employeeId){
        Employee employee =  crudApi.find(Employee.class, employeeId);
        if(employee == null){
           throw new DataNotFoundException("Employee with the ID: "+employeeId +" cannot be found!");
        }
        return employee;
    }
}
