/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.settings.WardType;
import com.khoders.ehrs.mapper.WardTypeMapper;
import com.khoders.ehrs.payload.WardTypeDto;
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
public class WardTypeService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private WardTypeMapper roomTypeMapper;
    @Inject private AppService appService;
   
    
    public WardTypeDto save(WardTypeDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            WardType emp = crudApi.find(WardType.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("RoomType with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        WardType wardType = roomTypeMapper.toEntity(dto);
        wardType.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(wardType) != null)
        {
            return roomTypeMapper.toDto(wardType);
        }
        return null;
    }
    
    public WardTypeDto findById(String roomTypeId)
    {
       WardType roomType = crudApi.find(WardType.class, roomTypeId);
       if (roomType == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return roomTypeMapper.toDto(roomType);
    }
    
    public List<WardTypeDto> roomTypeList(){
        List<WardTypeDto> dtoList = new LinkedList<>();
        List<WardType> roomTypeList = builder.findAll(WardType.class);
        for (WardType roomType : roomTypeList)
        {
            dtoList.add(roomTypeMapper.toDto(roomType));
        }
        return dtoList;
    }
    
    public boolean delete(String roomTypeId)
    {
        WardType roomType = crudApi.find(WardType.class, roomTypeId);
        if(roomType != null){
            return crudApi.delete(roomType);
        }
        return false;
    }
}
