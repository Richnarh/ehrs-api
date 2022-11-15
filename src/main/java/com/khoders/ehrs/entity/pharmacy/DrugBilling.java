/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.entity.pharmacy;

import com.khoders.ehrs.commons.BillingStatus;
import com.khoders.ehrs.entity.UserAccountRecord;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.patient.Prescription;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "drug_billing")
public class DrugBilling extends UserAccountRecord
{
    @JoinColumn(name = "prescription", referencedColumnName = "id")
    @ManyToOne
    private Prescription prescription;
    
    @Column(name = "billing_status")
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @ManyToOne
    private Employee employee;

    public Prescription getPrescription()
    {
        return prescription;
    }

    public void setPrescription(Prescription prescription)
    {
        this.prescription = prescription;
    }

    public BillingStatus getBillingStatus()
    {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus)
    {
        this.billingStatus = billingStatus;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
    
}
