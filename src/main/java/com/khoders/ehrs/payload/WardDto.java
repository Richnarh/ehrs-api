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
public class WardDto extends BaseDto
{
   private String wardNo;
   private String wardTypeName;   
   private String wardTypeId;  
   private String description;  

    public String getWardNo()
    {
        return wardNo;
    }

    public void setWardNo(String wardNo)
    {
        this.wardNo = wardNo;
    }

    public String getWardTypeName()
    {
        return wardTypeName;
    }

    public void setWardTypeName(String wardTypeName)
    {
        this.wardTypeName = wardTypeName;
    }

    public String getWardTypeId()
    {
        return wardTypeId;
    }

    public void setWardTypeId(String wardTypeId)
    {
        this.wardTypeId = wardTypeId;
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
