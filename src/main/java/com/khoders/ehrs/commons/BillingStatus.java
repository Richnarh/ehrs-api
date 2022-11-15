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
public enum BillingStatus implements MsgResolver
{
  APPROVED("APPROVED","Approved"), 
  UNAPPROVED("UNAPPROVED","Un Approved"),
  CLOS("CLOSE","Close");
  
  private String code;
  private String label;

    private BillingStatus(String code, String label)
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
