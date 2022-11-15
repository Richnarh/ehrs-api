/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.mapper.LeaveTypeMapper;
import com.khoders.ehrs.payload.LeaveTypeDto;
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
public class LeaveTypeService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private LeaveTypeMapper leaveTypeMapper;
    @Inject private AppService appService;
   
    
    public LeaveTypeDto save(LeaveTypeDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            LeaveType emp = crudApi.find(LeaveType.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("LeaveType with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        LeaveType leaveType = leaveTypeMapper.toEntity(dto);
        leaveType.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(leaveType) != null)
        {
            return leaveTypeMapper.toDto(leaveType);
        }
        return null;
    }
    
    public LeaveTypeDto findById(String leaveTypeId)
    {
       LeaveType leaveType = crudApi.find(LeaveType.class, leaveTypeId);
       if (leaveType == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return leaveTypeMapper.toDto(leaveType);
    }
    
    public List<LeaveTypeDto> leaveTypeList(){
        List<LeaveTypeDto> dtoList = new LinkedList<>();
        List<LeaveType> leaveTypeList = builder.findAll(LeaveType.class);
        for (LeaveType leaveType : leaveTypeList)
        {
            dtoList.add(leaveTypeMapper.toDto(leaveType));
        }
        return dtoList;
    }
    
    public boolean delete(String leaveTypeId)
    {
        LeaveType leaveType = crudApi.find(LeaveType.class, leaveTypeId);
        if(leaveType != null){
            return crudApi.delete(leaveType);
        }
        return false;
    }
}
