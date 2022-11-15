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
public class FrequencyDto extends BaseDto
{
  private String frequencyName;  

    public String getFrequencyName()
    {
        return frequencyName;
    }

    public void setFrequencyName(String frequencyName)
    {
        this.frequencyName = frequencyName;
    }
  
}
