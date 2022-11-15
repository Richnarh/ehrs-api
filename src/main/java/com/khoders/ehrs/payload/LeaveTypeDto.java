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
public class LeaveTypeDto extends BaseDto
{
    private String leaveName;
    private String description;

    public String getLeaveName()
    {
        return leaveName;
    }

    public void setLeaveName(String leaveName)
    {
        this.leaveName = leaveName;
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
