/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.mapper.JobRoleMapper;
import com.khoders.ehrs.payload.JobRoleDto;
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
public class JobRoleService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private JobRoleMapper jobRoleMapper;
    @Inject private AppService appService;
   
    
    public JobRoleDto save(JobRoleDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            JobRole emp = crudApi.find(JobRole.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("JobRole with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        JobRole jobRole = jobRoleMapper.toEntity(dto);
        jobRole.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(jobRole) != null)
        {
            return jobRoleMapper.toDto(jobRole);
        }
        return null;
    }
    
    public JobRoleDto findById(String jobRoleId)
    {
       JobRole jobRole = crudApi.find(JobRole.class, jobRoleId);
       if (jobRole == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return jobRoleMapper.toDto(jobRole);
    }
    
    public List<JobRoleDto> jobRoleList(){
        List<JobRoleDto> dtoList = new LinkedList<>();
        List<JobRole> jobRoleList = builder.findAll(JobRole.class);
        for (JobRole jobRole : jobRoleList)
        {
            dtoList.add(jobRoleMapper.toDto(jobRole));
        }
        return dtoList;
    }
    
    public boolean delete(String jobRoleId)
    {
        JobRole jobRole = crudApi.find(JobRole.class, jobRoleId);
        if(jobRole != null){
            return crudApi.delete(jobRole);
        }
        return false;
    }
}
