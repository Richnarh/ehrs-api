/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.mapper.EmployeeMapper;
import com.khoders.ehrs.payload.EmployeeDto;
import com.khoders.resource.enums.AccessLevel;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.Msg;
import static com.khoders.resource.utilities.SecurityUtil.hashText;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class EmployeeService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private EmployeeMapper employeeMapper;
    @Inject private AppService appService;
   
    
    public EmployeeDto save(EmployeeDto dto,String userAccountId)
    {
        if (dto.getId() != null){
            Employee emp = crudApi.find(Employee.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("Employee with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Employee employee = employeeMapper.toEntity(dto);
        employee.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(employee) != null){
            if(employee.isCanLogin()){
                UserAccount userAccount = new UserAccount();
                userAccount.setAccessLevel(AccessLevel.FRONT_DESK);
                userAccount.setEmailAddress(employee.getEmailAddress());
                userAccount.setPassword(hashText(employee.getEmailAddress()));
                userAccount.setJobRole(employee.getJobRole());
                userAccount.setPhoneNumber(employee.getPhoneNumber());
                userAccount.setTitle(employee.getTitle());
                userAccount.genCode();
                crudApi.save(userAccount);
            }
            return employeeMapper.toDto(employee);
        }
        return null;
    }
    
    public EmployeeDto findById(String employeeId)
    {
       Employee employee = crudApi.find(Employee.class, employeeId);
       if (employee == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return employeeMapper.toDto(employee);
    }
    
    public List<EmployeeDto> employeeList(){
        List<EmployeeDto> dtoList = new LinkedList<>();
        List<Employee> employeeList = builder.findAll(Employee.class);
        
        for (Employee employee : employeeList)
        {
            dtoList.add(employeeMapper.toDto(employee));
        }
        return dtoList;
    }
    
    public boolean delete(String employeeId)
    {
        Employee employee = crudApi.find(Employee.class, employeeId);
        if(employee != null){
            return crudApi.delete(employee);
        }
        return false;
    }
}
