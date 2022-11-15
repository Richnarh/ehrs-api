/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.payload.EmployeeDto;
import com.khoders.resource.enums.IdType;
import com.khoders.resource.enums.Title;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class EmployeeMapper
{
    @Inject private CrudApi crudApi;
    
    public Employee toEntity(EmployeeDto dto){
        Employee employee = new Employee();
        if (dto.getId() != null){
            employee.setId(dto.getId());
        }
        
        employee.setFirstname(dto.getFirstname());
        employee.setMiddlename(dto.getMiddlename());
        employee.setLastname(dto.getLastname());
        if(dto.getJobRoleId() == null){
            throw new DataNotFoundException("Please Specify JobRole Id");
        }
        JobRole jobRole = crudApi.find(JobRole.class, dto.getJobRoleId());
        if(jobRole != null){
            employee.setJobRole(jobRole);
        }
        
        if(dto.getDepartmentId() == null){
            throw new DataNotFoundException("Please Specify Department Id");
        }
        Department department = crudApi.find(Department.class, dto.getDepartmentId());
        if(jobRole != null){
            employee.setDepartment(department);
        }
        employee.setAddress(dto.getAddress());
        employee.setEmailAddress(dto.getEmailAddress());
        employee.setIdType(IdType.valueOf(dto.getIdType()));
        employee.setIdNumber(dto.getIdNumber());
        employee.setSsnitNo(dto.getSsnitNo());
        employee.setTitle(Title.valueOf(dto.getTitle()));    
        employee.genCode();
        employee.setFullname(dto.getFirstname() +" "+dto.getMiddlename()+" "+dto.getLastname());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setCanLogin(dto.isCanLogin());
        return employee;
    }
    
    public EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        if(employee.getId() == null) return null;
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstname());
        dto.setMiddlename(employee.getMiddlename());
        dto.setLastname(employee.getLastname());
        if(employee.getDepartment() != null){
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        if(employee.getJobRole()!= null){
            dto.setJobRoleId(employee.getJobRole().getId());
            dto.setJobRole(employee.getJobRole().getRoleName());
        }
        dto.setAddress(employee.getAddress());
        dto.setIdType(employee.getIdType() != null ? employee.getIdType().getLabel() : "");
        dto.setIdNumber(employee.getIdNumber());
        dto.setTitle(employee.getTitle().getLabel());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setSsnitNo(employee.getSsnitNo());
        dto.setEmailAddress(employee.getEmailAddress());
        dto.setRefNo(employee.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(employee.getValueDate(), Pattern.ddMMyyyy));
        dto.setCanLogin(employee.isCanLogin());
        return dto;
    }
}
