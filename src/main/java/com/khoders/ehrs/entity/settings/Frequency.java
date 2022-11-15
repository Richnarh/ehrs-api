/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.settings;

import com.khoders.ehrs.entity.UserAccountRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "frequency")
public class Frequency extends UserAccountRecord
{
    @Column(name = "frequency_name")
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
