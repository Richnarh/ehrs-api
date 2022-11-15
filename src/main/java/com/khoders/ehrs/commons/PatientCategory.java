/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.commons;

import com.khoders.resource.utilities.MsgResolver;

/**
 *
 * @author richa
 */
public enum PatientCategory implements MsgResolver
{
  ADULT("ADULT", "Adult"),
  CHILD("CHILD", "Child");

  private final String code;
  private final String label;

    private PatientCategory(String code, String label)
    {
        this.code = code;
        this.label = label;
    }
  
    @Override
    public String getCode()
    {
       return code;
    }

    @Override
    public String getLabel()
    {
       return label;
    }

    @Override
    public String toString()
    {
        return label;
    }
    
}
