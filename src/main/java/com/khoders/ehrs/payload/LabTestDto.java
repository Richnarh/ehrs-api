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
public class LabTestDto extends BaseDto
{
  private String labName;
  private String labId;
  private String testType;
  private String testTypeId;
  private String patientName;
  private String patientId;
  private String doctorName;
  private String doctorId;
  private String testDate;  
  private String price;  

    public String getLabName()
    {
        return labName;
    }

    public void setLabName(String labName)
    {
        this.labName = labName;
    }

    public String getLabId()
    {
        return labId;
    }

    public void setLabId(String labId)
    {
        this.labId = labId;
    }

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

    public String getDoctorName()
    {
        return doctorName;
    }

    public void setDoctorName(String doctorName)
    {
        this.doctorName = doctorName;
    }

    public String getDoctorId()
    {
        return doctorId;
    }

    public void setDoctorId(String doctorId)
    {
        this.doctorId = doctorId;
    }

    public String getTestDate()
    {
        return testDate;
    }

    public void setTestDate(String testDate)
    {
        this.testDate = testDate;
    }

    public String getTestType()
    {
        return testType;
    }

    public void setTestType(String testType)
    {
        this.testType = testType;
    }

    public String getTestTypeId()
    {
        return testTypeId;
    }

    public void setTestTypeId(String testTypeId)
    {
        this.testTypeId = testTypeId;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
  
}
