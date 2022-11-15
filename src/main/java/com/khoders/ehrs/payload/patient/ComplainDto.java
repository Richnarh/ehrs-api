/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload.patient;

import com.khoders.ehrs.payload.BaseDto;

/**
 *
 * @author richa
 */
public class ComplainDto extends BaseDto
{
   private String complains; 

    public String getComplains()
    {
        return complains;
    }

    public void setComplains(String complains)
    {
        this.complains = complains;
    }
   
}
