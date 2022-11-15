/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.payload.LeaveTypeDto;
import com.khoders.resource.jpa.CrudApi;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class LeaveTypeMapper
{
   @Inject private CrudApi crudApi;
   
   public LeaveType toEntity(LeaveTypeDto dto){
       LeaveType leave = new LeaveType();
       if (dto.getId() != null){
            leave.setId(dto.getId());
        }
       leave.setDescription(dto.getDescription());

       leave.setLeaveName(dto.getLeaveName());
       leave.genCode();
       
       return leave;
   }
   
   public LeaveTypeDto toDto(LeaveType leave){
       if(leave.getId() == null) return null;
       LeaveTypeDto dto = new LeaveTypeDto();
       dto.setId(leave.getId());
       dto.setDescription(leave.getDescription());
       dto.setLeaveName(leave.getLeaveName());
       
       return dto;
   }
}
