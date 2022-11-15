/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.settings;

import com.khoders.ehrs.entity.UserAccountRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "job_role")
public class JobRole extends UserAccountRecord
{
   @Column(name = "role_name")
   private String roleName;
   
   @Column(name = "description")
   @Lob
   private String description;

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return roleName;
    }
   
}
