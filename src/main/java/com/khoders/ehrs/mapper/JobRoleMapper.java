/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.payload.JobRoleDto;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class JobRoleMapper
{
    @Inject private CrudApi crudApi;
    
    public JobRole toEntity(JobRoleDto dto){
        JobRole jobRole = new JobRole();
        if (dto.getId() != null){
            jobRole.setId(dto.getId());
        }   
        jobRole.setRoleName(dto.getRoleName());
        jobRole.setDescription(dto.getDescription());
        jobRole.genCode();
        return jobRole;
    }
    
    public JobRoleDto toDto(JobRole jobRole){
        JobRoleDto dto = new JobRoleDto();
        if(jobRole.getId() == null) return null;
        dto.setId(jobRole.getId());
        
        dto.setRoleName(jobRole.getRoleName());
        dto.setDescription(jobRole.getDescription());
        dto.setRefNo(jobRole.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(jobRole.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
