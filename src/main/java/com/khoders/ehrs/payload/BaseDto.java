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
public class BaseDto
{
    private String id;
    private String refNo;
    private String valueDate;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRefNo()
    {
        return refNo;
    }

    public void setRefNo(String refNo)
    {
        this.refNo = refNo;
    }

    public String getValueDate()
    {
        return valueDate;
    }

    public void setValueDate(String valueDate)
    {
        this.valueDate = valueDate;
    }
}
