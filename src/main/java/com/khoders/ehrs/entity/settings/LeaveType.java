/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.settings;

import com.khoders.ehrs.entity.Ref;
import com.khoders.ehrs.entity.UserAccountRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name="leave_types")
public class LeaveType extends UserAccountRecord
{
    @Column(name="leave_name")
    private String leaveName;
    
    @Column(name="description")
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
