/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.entity.leave.SpecialLeave;
import com.khoders.ehrs.payload.SpecialLeaveDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class SpecialLeaveMapper
{
   @Inject private CrudApi crudApi;
   
   public SpecialLeave toEntity(SpecialLeaveDto dto){
       SpecialLeave leave = new SpecialLeave();
       if (dto.getId() != null){
            leave.setId(dto.getId());
        }
       leave.setDescription(dto.getDescription());
       if(dto.getLeaveTypeId()== null){
            throw new DataNotFoundException("Please Specify roomType Id");
        }
       if(dto.getEmployeeId()== null){
            throw new DataNotFoundException("Please Specify employee Id");
        }
       
       LeaveType leaveType = crudApi.find(LeaveType.class, dto.getLeaveTypeId());
       if(leaveType != null){
           leave.setLeaveType(leaveType);
       }
       Employee employee = crudApi.find(Employee.class, dto.getEmployeeId());
       if(employee != null){
           leave.setEmployee(employee);
       }
       leave.setMaxNumberOfDays(dto.getMaxNumberOfDays());
       leave.setPurpose(dto.getPurpose());
       leave.genCode();
       
       return leave;
   }
   
   public SpecialLeaveDto toDto(SpecialLeave leave){
       if(leave.getId() == null) return null;
       SpecialLeaveDto dto = new SpecialLeaveDto();
       dto.setId(leave.getId());
       dto.setDescription(leave.getDescription());
       dto.setPurpose(leave.getPurpose());
       dto.setMaxNumberOfDays(leave.getMaxNumberOfDays());
       if(leave.getEmployee() != null){
           dto.setEmployeeId(leave.getEmployee().getId());
           dto.setEmployeeName(leave.getEmployee().getFullname());
       }
       if(leave.getLeaveType() != null){
           dto.setLeaveTypeId(leave.getLeaveType().getId());
           dto.setLeaveTypeName(leave.getLeaveType().getLeaveName());
       }
       
       return dto;
   }
}
