/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.lab;

import com.khoders.ehrs.entity.PatientDatedRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "test_type")
public class TestType extends PatientDatedRecord
{
    @Column(name = "test_type_name")
    private String testTypeName;
    
    @Column(name = "description")
    @Lob
    private String description;

    public String getTestTypeName()
    {
        return testTypeName;
    }

    public void setTestTypeName(String testTypeName)
    {
        this.testTypeName = testTypeName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
