/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.lab.Lab;
import com.khoders.ehrs.mapper.LabMapper;
import com.khoders.ehrs.payload.LabDto;
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
public class LabService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private LabMapper labMapper;
    @Inject private AppService appService;
   
    
    public LabDto save(LabDto dto,String userAccountId)
    {
        if (dto.getId() != null){
            Lab emp = crudApi.find(Lab.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("Lab with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Lab lab = labMapper.toEntity(dto);
        lab.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(lab) != null)
        {
            return labMapper.toDto(lab);
        }
        return null;
    }
    
    public LabDto findById(String labId)
    {
       Lab lab = crudApi.find(Lab.class, labId);
       if (lab == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return labMapper.toDto(lab);
    }
    
    public List<LabDto> labList(){
        List<LabDto> dtoList = new LinkedList<>();
        List<Lab> labList = builder.findAll(Lab.class);
        for (Lab lab : labList)
        {
            dtoList.add(labMapper.toDto(lab));
        }
        return dtoList;
    }
    
    public boolean delete(String labId)
    {
        Lab lab = crudApi.find(Lab.class, labId);
        if(lab != null){
            return crudApi.delete(lab);
        }
        return false;
    }
}
