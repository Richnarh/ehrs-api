/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.lab.Lab;
import com.khoders.ehrs.payload.LabDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import com.khoders.resource.utilities.SystemUtils;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class LabMapper
{
    @Inject private CrudApi crudApi;
    
    public Lab toEntity(LabDto dto){
        Lab lab = new Lab();
        if (dto.getId() != null)
        {
            lab.setId(dto.getId());
        }
        if(dto.getUnitHeadId() == null){
            throw new DataNotFoundException("Please Specify UnitHead Id");
        }
        Employee unitHead = crudApi.find(Employee.class, dto.getUnitHeadId());
        if(unitHead != null){
            lab.setUnitHead(unitHead);
        }
        lab.setUnitNo(dto.getUnitNo() == null ? SystemUtils.generateShortCode() : dto.getUnitNo());
        lab.setUnitName(dto.getUnitName());
        lab.genCode();
            
        return lab;
    }
    
    public LabDto toDto(Lab lab){
        LabDto dto = new LabDto();
        if(lab.getId() == null) return null;
        dto.setId(lab.getId());
        
        if(lab.getUnitHead()!= null){
            dto.setUnitHeadId(lab.getUnitHead().getId());
            dto.setUnitHeadName(lab.getUnitHead().getFirstname() +" "+lab.getUnitHead().getLastname());
        }
        dto.setUnitNo(lab.getUnitNo());
        dto.setUnitName(lab.getUnitName());
        dto.setValueDate(DateUtil.parseLocalDateString(lab.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
