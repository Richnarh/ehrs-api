/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.patient.Ward;
import com.khoders.ehrs.entity.settings.WardType;
import com.khoders.ehrs.payload.WardDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class WardMapper
{
    @Inject private CrudApi crudApi;
    
    public Ward toEntity(WardDto dto){
        Ward room = new Ward();
        if (dto.getId() != null)
        {
            room.setId(dto.getId());
        }
        room.setWardNo(dto.getWardNo());
        if(dto.getWardTypeId() == null){
            throw new DataNotFoundException("Please Specify roomType Id");
        }
        WardType roomType = crudApi.find(WardType.class, dto.getWardTypeId());
        if(roomType != null){
            room.setWardType(roomType);
        }
            
        return room;
    }
    
    public WardDto toDto(Ward ward){
        WardDto dto = new WardDto();
        if(ward.getId() == null) return null;
        dto.setId(ward.getId());
        
        if(ward.getWardType() != null){
            dto.setWardTypeId(ward.getWardType().getId());
            dto.setWardTypeName(ward.getWardType().getWardName());
        }
        dto.setWardNo(ward.getWardNo());
        dto.setDescription(ward.getDescription());
        dto.setValueDate(DateUtil.parseLocalDateString(ward.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
