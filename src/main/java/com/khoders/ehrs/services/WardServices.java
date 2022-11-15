/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.patient.Ward;
import com.khoders.ehrs.mapper.WardMapper;
import com.khoders.ehrs.payload.WardDto;
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
 * @author richa
 */
@Stateless
public class WardServices
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private WardMapper wardMapper;
    @Inject private AppService appService;
   
    
    public WardDto save(WardDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Ward emp = crudApi.find(Ward.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("Room with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Ward ward = wardMapper.toEntity(dto);
        ward.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(ward) != null)
        {
            return wardMapper.toDto(ward);
        }
        return null;
    }
    
    public WardDto findById(String roomId)
    {
       Ward room = crudApi.find(Ward.class, roomId);
       if (room == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return wardMapper.toDto(room);
    }
    
    public List<WardDto> roomList(){
        List<WardDto> dtoList = new LinkedList<>();
        List<Ward> roomList = builder.findAll(Ward.class);
        for (Ward room : roomList)
        {
            dtoList.add(wardMapper.toDto(room));
        }
        return dtoList;
    }
    
    public boolean delete(String roomId)
    {
        Ward room = crudApi.find(Ward.class, roomId);
        if(room != null){
            return crudApi.delete(room);
        }
        return false;
    }
}
