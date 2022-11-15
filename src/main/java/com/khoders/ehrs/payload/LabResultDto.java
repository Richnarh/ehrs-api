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
public class LabResultDto extends BaseDto
{
  private String labTest;
  private String labTestId;
  private String patientName;
  private String patientId;
  private String testResult; 
  private double price; 

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public void setPatientId(String patientId)
    {
        this.patientId = patientId;
    }

    public String getTestResult()
    {
        return testResult;
    }

    public void setTestResult(String testResult)
    {
        this.testResult = testResult;
    }

    public String getLabTest()
    {
        return labTest;
    }

    public void setLabTest(String labTest)
    {
        this.labTest = labTest;
    }

    public String getLabTestId()
    {
        return labTestId;
    }

    public void setLabTestId(String labTestId)
    {
        this.labTestId = labTestId;
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
