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
public class TestTypeDto extends BaseDto{
    private String testTypeName;
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
