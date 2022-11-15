/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.lab;

import com.khoders.ehrs.entity.PatientDatedRecord;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "lab_test")
public class LabTest extends PatientDatedRecord
{
  @JoinColumn(name = "lab", referencedColumnName = "id")
  @ManyToOne
  private Lab lab;
  
  @JoinColumn(name = "test_type", referencedColumnName = "id")
  @ManyToOne
  private TestType testType;
  
  @Column(name = "test_date")
  private LocalDate testDate;

    public Lab getLab()
    {
        return lab;
    }

    public void setLab(Lab lab)
    {
        this.lab = lab;
    }

    public LocalDate getTestDate()
    {
        return testDate;
    }

    public void setTestDate(LocalDate testDate)
    {
        this.testDate = testDate;
    }

    public TestType getTestType()
    {
        return testType;
    }

    public void setTestType(TestType testType)
    {
        this.testType = testType;
    }
  
}
