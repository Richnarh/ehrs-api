/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.payload.patient;

import com.khoders.ehrs.payload.BaseDto;

/**
 *
 * @author richa
 */
public class DrReportDto extends BaseDto
{
    private String comment;
    private String complain;
    private String complainId;
    private String assignPatient;

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getAssignPatient()
    {
        return assignPatient;
    }

    public void setAssignPatient(String assignPatient)
    {
        this.assignPatient = assignPatient;
    }

    public String getComplain()
    {
        return complain;
    }

    public void setComplain(String complain)
    {
        this.complain = complain;
    }

    public String getComplainId()
    {
        return complainId;
    }

    public void setComplainId(String complainId)
    {
        this.complainId = complainId;
    }
    
}
