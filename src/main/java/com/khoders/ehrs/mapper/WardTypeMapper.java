/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.settings.WardType;
import com.khoders.ehrs.payload.WardTypeDto;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class WardTypeMapper
{
    @Inject private CrudApi crudApi;
    
    public WardType toEntity(WardTypeDto dto){
        WardType wardType = new WardType();
        if (dto.getId() != null){
            wardType.setId(dto.getId());
        }   
        wardType.setWardName(dto.getWardName());
        wardType.genCode();
        return wardType;
    }
    
    public WardTypeDto toDto(WardType wardType){
        WardTypeDto dto = new WardTypeDto();
        if(wardType.getId() == null) return null;
        dto.setId(wardType.getId());
        
        dto.setWardName(wardType.getWardName());
        dto.setRefNo(wardType.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(wardType.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
