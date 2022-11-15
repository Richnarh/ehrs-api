/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.settings.Frequency;
import com.khoders.ehrs.payload.FrequencyDto;
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
public class FrequencyService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private AppService appService;
   
    
    public FrequencyDto save(FrequencyDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Frequency frequency = crudApi.find(Frequency.class, dto.getId());
            if (frequency == null){
                throw new DataNotFoundException("frequency with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Frequency frequency = toEntity(dto);
        frequency.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(frequency) != null)
        {
            return toDto(frequency);
        }
        return null;
    }
    
    public FrequencyDto findById(String frequencyId)
    {
       Frequency frequency = crudApi.find(Frequency.class, frequencyId);
       if (frequency == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return toDto(frequency);
    }
    
    public List<FrequencyDto> frequencyList(){
        List<FrequencyDto> dtoList = new LinkedList<>();
        List<Frequency> frequencys = builder.findAll(Frequency.class);
        for (Frequency frequency : frequencys)
        {
            dtoList.add(toDto(frequency));
        }
        return dtoList;
    }
    
    public boolean delete(String frequencyId)
    {
        Frequency frequency = crudApi.find(Frequency.class, frequencyId);
        if(frequency != null){
            return crudApi.delete(frequency);
        }
        return false;
    }
    
    
    public Frequency toEntity(FrequencyDto dto){
        Frequency frequency = new Frequency();
        if (dto.getId() != null){
            frequency.setId(dto.getId());
        }   
        frequency.setFrequencyName(dto.getFrequencyName());
        frequency.genCode();
        return frequency;
    }
    
    public FrequencyDto toDto(Frequency frequency){
        FrequencyDto dto = new FrequencyDto();
        if(frequency.getId() == null) return null;
        dto.setId(frequency.getId());
        
        dto.setFrequencyName(frequency.getFrequencyName());
        dto.setRefNo(frequency.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(frequency.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
