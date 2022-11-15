/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.pharmacy.Inventory;
import com.khoders.ehrs.payload.InventoryDto;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
public class InventoryMapper{
    @Inject private CrudApi crudApi;
    public Inventory toEntity(InventoryDto dto) {
        Inventory inventory = new Inventory();
        if (dto.getId() != null) {
            inventory.setId(dto.getId());
        }
        inventory.genCode();
        inventory.setProductName(dto.getProductName());
        inventory.setQuantity(dto.getQuantity());
        inventory.setAmount(dto.getAmount());
        inventory.setSellingPrice(dto.getSellingPrice());
        inventory.setExpiryDate(DateUtil.parseLocalDate(dto.getExpiryDate(), Pattern._yyyyMMdd));
        inventory.setBatchNo(dto.getBatchNo());
        inventory.setReorderLevel(dto.getReorderLevel());
        return inventory;
    }
    
    public InventoryDto toDto(Inventory inventory){
        InventoryDto dto = new InventoryDto();
        if(inventory.getId() == null) return null;
        dto.setId(inventory.getId());
        dto.setRefNo(inventory.getRefNo());
        dto.setProductName(inventory.getProductName());
        dto.setQuantity(inventory.getQuantity());
        dto.setAmount(inventory.getAmount());
        dto.setSellingPrice(inventory.getSellingPrice());
        dto.setExpiryDate(DateUtil.parseLocalDateString(inventory.getExpiryDate(), Pattern.ddMMyyyy));
        dto.setBatchNo(inventory.getBatchNo());
        dto.setReorderLevel(inventory.getReorderLevel());
        
        return dto;
    }
}
