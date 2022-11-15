/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.lab.TestType;
import com.khoders.ehrs.payload.TestTypeDto;
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
public class TestTypeService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private AppService appService;
   
    
    public TestTypeDto save(TestTypeDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            TestType emp = crudApi.find(TestType.class, dto.getId());
            if (emp == null){
                throw new DataNotFoundException("TestType with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        TestType testType = toEntity(dto);
        testType.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(testType) != null)
        {
            return toDto(testType);
        }
        return null;
    }
    
    public TestTypeDto findById(String roomTypeId)
    {
       TestType roomType = crudApi.find(TestType.class, roomTypeId);
       if (roomType == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return toDto(roomType);
    }
    
    public List<TestTypeDto> roomTypeList(){
        List<TestTypeDto> dtoList = new LinkedList<>();
        List<TestType> roomTypeList = builder.findAll(TestType.class);
        for (TestType roomType : roomTypeList)
        {
            dtoList.add(toDto(roomType));
        }
        return dtoList;
    }
    
    public boolean delete(String roomTypeId)
    {
        TestType roomType = crudApi.find(TestType.class, roomTypeId);
        if(roomType != null){
            return crudApi.delete(roomType);
        }
        return false;
    }
    
    public TestType toEntity(TestTypeDto dto){
        TestType wardType = new TestType();
        if (dto.getId() != null){
            wardType.setId(dto.getId());
        }   
        wardType.setTestTypeName(dto.getTestTypeName());
        wardType.setDescription(dto.getDescription());
        wardType.genCode();
        return wardType;
    }
    
    public TestTypeDto toDto(TestType testType){
        TestTypeDto dto = new TestTypeDto();
        if(testType.getId() == null) return null;
        dto.setId(testType.getId());
        
        dto.setTestTypeName(testType.getTestTypeName());
        dto.setDescription(testType.getDescription());
        dto.setRefNo(testType.getRefNo());
        dto.setValueDate(DateUtil.parseLocalDateString(testType.getValueDate(), Pattern.ddMMyyyy));
        return dto;
    }
}
