/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.pharmacy.Inventory;
import com.khoders.ehrs.mapper.InventoryMapper;
import com.khoders.ehrs.payload.InventoryDto;
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
public class InventoryService
{
    @Inject private CrudApi crudApi;
    @Inject private QueryBuilder builder;
    @Inject private InventoryMapper inventoryMapper;
    @Inject private AppService appService;
   
    
    public InventoryDto save(InventoryDto dto, String userAccountId)
    {
        if (dto.getId() != null){
            Inventory inventory = crudApi.find(Inventory.class, dto.getId());
            if (inventory == null){
                throw new DataNotFoundException("Inventory with ID: "+ dto.getId() +" Not Found");
            }
        }
        
        Inventory inventory = inventoryMapper.toEntity(dto);
        inventory.setUserAccount(appService.getUser(userAccountId));
        if(crudApi.save(inventory) != null){
            return inventoryMapper.toDto(inventory);
        }
        return null;
    }
    
    public InventoryDto findById(String inventoryId)
    {
       Inventory inventory = crudApi.find(Inventory.class, inventoryId);
       if (inventory == null){
            throw new DataNotFoundException(Msg.RECORD_NOT_FOUND);
        }
       return inventoryMapper.toDto(inventory);
    }
    
    public List<InventoryDto> inventoryList(){
        List<InventoryDto> dtoList = new LinkedList<>();
        List<Inventory> inventoryList = builder.findAll(Inventory.class);
        for (Inventory inventory : inventoryList)
        {
            dtoList.add(inventoryMapper.toDto(inventory));
        }
        return dtoList;
    }
    
    public boolean delete(String inventoryId)
    {
        Inventory inventory = crudApi.find(Inventory.class, inventoryId);
        if(inventory != null){
            return crudApi.delete(inventory);
        }
        return false;
    }
}
