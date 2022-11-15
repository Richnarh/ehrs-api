/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.patient.Billing;
import com.khoders.ehrs.mapper.BillingMapper;
import com.khoders.ehrs.payload.patient.BillingDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.Msg;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class BillingService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private AppService appService;
    @Inject private BillingMapper billingMapper;
       
    public BillingDto save(String userAccountId, BillingDto dto)
    {
        if (dto.getId() != null){
            Billing emp = crudApi.find(Billing.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("Billing with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Billing billing = billingMapper.toEntity(dto);
        billing.setUserAccount(appService.getUser(userAccountId));
        billing.setPatient(appService.getPatient(dto.getPatientId()));
        if(crudApi.save(billing) != null)
        {
            return billingMapper.toDto(billing);
        }
        return null;
    }
    
    public BillingDto findById(String billingId)
    {
       Billing billing = crudApi.find(Billing.class, billingId);
       if (billing == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return billingMapper.toDto(billing);
    }
    
    public List<BillingDto> billingList(){
        List<BillingDto> dtoList = new LinkedList<>();
        List<Billing> billingList = builder.findAll(Billing.class);

        for (Billing billing : billingList)
        {
            dtoList.add(billingMapper.toDto(billing));
        }
        return dtoList;
    }
    
    public boolean delete(String billingId)
    {
        Billing billing = crudApi.find(Billing.class, billingId);
        if(billing != null){
            return crudApi.delete(billing);
        }
        return false;
    }
}
