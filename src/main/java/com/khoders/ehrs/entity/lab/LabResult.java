/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.lab;

import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.patient.Patient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "lab_result")
public class LabResult extends UserAccountRecord
{
  @JoinColumn(name = "lab_test", referencedColumnName = "id")
  @ManyToOne
  private LabTest labTest;
  
  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne
  private Patient patient;
  
  @Column(name = "price")
  private double price;
  
  @Column(name = "test_result")
  @Lob
  private String testResult;

    public LabTest getLabTest()
    {
        return labTest;
    }

    public void setLabTest(LabTest labTest)
    {
        this.labTest = labTest;
    }

  
    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public String getTestResult()
    {
        return testResult;
    }

    public void setTestResult(String testResult)
    {
        this.testResult = testResult;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
  
  
}
