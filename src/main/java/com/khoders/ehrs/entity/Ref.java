/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity;

import com.khoders.resource.jpa.BaseModel;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author richa
 */
@MappedSuperclass
public class Ref extends BaseModel implements Serializable
{
    @Column(name = "ref_no")
    private String refNo;

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
   
    public void genCode()
    {
        if (getRefNo() != null)
        {
            setRefNo(getRefNo());
        } else
        {
            setRefNo(SystemUtils.generateCode());
        }
    } 
}
