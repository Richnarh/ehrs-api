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
public class LabDto extends BaseDto
{
    private String unitNo;
    private String unitName;
    private String unitHeadName;   
    private String unitHeadId;   

    public String getUnitNo()
    {
        return unitNo;
    }

    public void setUnitNo(String unitNo)
    {
        this.unitNo = unitNo;
    }

    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getUnitHeadName()
    {
        return unitHeadName;
    }

    public void setUnitHeadName(String unitHeadName)
    {
        this.unitHeadName = unitHeadName;
    }

    public String getUnitHeadId()
    {
        return unitHeadId;
    }

    public void setUnitHeadId(String unitHeadId)
    {
        this.unitHeadId = unitHeadId;
    }
    
}
