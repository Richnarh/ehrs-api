/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.payload.DepartmentDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richard
 */
public class DeptMapper
{
    @Inject private CrudApi crudApi;
    
    public Department toEntity(DepartmentDto dto){
        Department department = new Department();
        if (dto.getId() != null)
        {
            department.setId(dto.getId());
        }
        
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDepartmentName(dto.getDepartmentName());
        if(dto.getHodId() == null){
            throw new DataNotFoundException("Please Specify Employee Id");
        }
        
        Employee employee = crudApi.find(Employee.class, dto.getHodId());
        if(employee != null){
            department.setHod(employee);
        }
        
        return department;
    }
    
    public DepartmentDto toDto(Department department){
        DepartmentDto dto = new DepartmentDto();
        if(department.getId() == null) return null;
        dto.setId(department.getId());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDepartmentName(department.getDepartmentName());
        if(department.getHod() != null){
            dto.setHodId(department.getHod().getId());
            dto.setHodName(department.getHod().getFirstname() +" "+department.getHod().getLastname());
        }
        dto.setValueDate(DateUtil.parseLocalDateString(department.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
