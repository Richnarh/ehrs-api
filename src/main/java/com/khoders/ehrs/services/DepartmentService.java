/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.mapper.DeptMapper;
import com.khoders.ehrs.payload.DepartmentDto;
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
public class DepartmentService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private DeptMapper deptMapper;
    @Inject private AppService appService;
   
    
    public DepartmentDto save(DepartmentDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Department dept = crudApi.find(Department.class, dto.getId());
            if (dept == null){
                throw new DataNotFoundException("Department with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Department department = deptMapper.toEntity(dto);
        department.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(department) != null)
        {
            return deptMapper.toDto(department);
        }
        return null;
    }
    
    public DepartmentDto findById(String departmentId)
    {
       Department department = crudApi.find(Department.class, departmentId);
       if (department == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return deptMapper.toDto(department);
    }
    
    public List<DepartmentDto> departmentList()
    {
        List<DepartmentDto> dtoList = new LinkedList<>();
        List<Department> departmentList = builder.findAll(Department.class);
        for (Department department : departmentList)
        {
            dtoList.add(deptMapper.toDto(department));
        }
        return dtoList;
    }
        
    public boolean delete(String departmentId)
    {
        Department department = crudApi.find(Department.class, departmentId);
        if(department != null){
            return crudApi.delete(department);
        }
        return false;
    }
}
