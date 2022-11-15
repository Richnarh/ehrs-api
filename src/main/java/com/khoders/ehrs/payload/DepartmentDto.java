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
public class DepartmentDto extends BaseDto
{
    private String departmentCode;
    private String departmentName;
    private String hodName; 
    private String hodId; 

    public String getDepartmentCode()
    {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getHodName()
    {
        return hodName;
    }

    public void setHodName(String hodName)
    {
        this.hodName = hodName;
    }

    public String getHodId()
    {
        return hodId;
    }

    public void setHodId(String hodId)
    {
        this.hodId = hodId;
    }
    
}
