/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.leave.SpecialLeave;
import com.khoders.ehrs.mapper.SpecialLeaveMapper;
import com.khoders.ehrs.payload.SpecialLeaveDto;
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
public class SpecialLeaveService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private SpecialLeaveMapper specialLeaveMapper;
    @Inject private AppService appService;
   
    
    public SpecialLeaveDto save(SpecialLeaveDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            SpecialLeave emp = crudApi.find(SpecialLeave.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("SpecialLeave with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        SpecialLeave specialLeave = specialLeaveMapper.toEntity(dto);
        specialLeave.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(specialLeave) != null)
        {
            return specialLeaveMapper.toDto(specialLeave);
        }
        return null;
    }
    
    public SpecialLeaveDto findById(String specialLeaveId)
    {
       SpecialLeave specialLeave = crudApi.find(SpecialLeave.class, specialLeaveId);
       if (specialLeave == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return specialLeaveMapper.toDto(specialLeave);
    }
    
    public List<SpecialLeaveDto> specialLeaveList(){
        List<SpecialLeaveDto> dtoList = new LinkedList<>();
        List<SpecialLeave> specialLeaveList = builder.findAll(SpecialLeave.class);
        for (SpecialLeave specialLeave : specialLeaveList)
        {
            dtoList.add(specialLeaveMapper.toDto(specialLeave));
        }
        return dtoList;
    }
    
    public boolean delete(String specialLeaveId)
    {
        SpecialLeave specialLeave = crudApi.find(SpecialLeave.class, specialLeaveId);
        if(specialLeave != null){
            return crudApi.delete(specialLeave);
        }
        return false;
    }
}
