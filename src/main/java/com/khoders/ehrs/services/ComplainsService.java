/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.patient.Complain;
import com.khoders.ehrs.payload.patient.ComplainDto;
import com.khoders.resource.exception.DataNotFoundException;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.Pattern;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pascal
 */
@Stateless
public class ComplainsService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private AppService appService;
    
    public ComplainDto save(ComplainDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Complain emp = crudApi.find(Complain.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("Complain with ID: "+ dto.getId() +" Not Found");
            } 
        }
        Complain complain = toEntity(dto);
        complain.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(complain) != null){
            return toDto(complain);
        }
        return null;
    }
    
    public ComplainDto findById(String complainId)
    {
       Complain complain = crudApi.find(Complain.class, complainId);
       if (complain == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return toDto(complain);
    }
    
    public List<ComplainDto> complainList(){
        List<ComplainDto> dtoList = new LinkedList<>();
        List<Complain> complainList = builder.findAll(Complain.class);
        for (Complain complain : complainList){
            dtoList.add(toDto(complain));
        }
        return dtoList;
    }
    
    public boolean delete(String complainId){
        Complain complain = crudApi.find(Complain.class, complainId);
        if(complain != null){
            return crudApi.delete(complain);
        }
        return false;
    }
    
    public Complain toEntity(ComplainDto dto){
       Complain complain = new Complain();
       if (dto.getId() != null){
            complain.setId(dto.getId());
        }
       complain.setComplains(dto.getComplains());
       complain.genCode();
       
       return complain;
   }
   
   public ComplainDto toDto(Complain complain){
       if(complain.getId() == null) return null;
       ComplainDto dto = new ComplainDto();
       dto.setId(complain.getId());
       dto.setComplains(complain.getComplains());
       dto.setRefNo(complain.getRefNo());
       dto.setValueDate(DateUtil.parseLocalDateString(complain.getValueDate(), Pattern.ddMMyyyy));
       return dto;
   }
}
