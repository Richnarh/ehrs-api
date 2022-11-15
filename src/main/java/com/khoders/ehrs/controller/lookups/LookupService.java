/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller.lookups;

import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.entity.patient.Ward;
import com.khoders.ehrs.entity.settings.WardType;
import com.khoders.ehrs.entity.lab.Lab;
import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.lab.TestType;
import com.khoders.ehrs.entity.patient.DrReport;
import com.khoders.ehrs.entity.settings.LeaveType;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.entity.pharmacy.Inventory;
import com.khoders.ehrs.entity.settings.Frequency;
import com.khoders.ehrs.payload.LookupItem;
import com.khoders.resource.jpa.QueryBuilder;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
@Stateless
public class LookupService
{
    @Inject private QueryBuilder builder;
    
     public List<LookupItem> department(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Department.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getDepartmentName());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> leaveType(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(LeaveType.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getLeaveName());
                itemList.add(item);
            });
        return itemList;
    }  
   
    public List<LookupItem> roomType(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(WardType.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getWardName());
                itemList.add(item);
            });
        return itemList;
    }  
   
    public List<LookupItem> jobRole(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(JobRole.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getRoleName());
                itemList.add(item);
            });
        return itemList;
    }  
   
    public List<LookupItem> employee(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Employee.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getFirstname() +" "+data.getLastname());
                itemList.add(item);
            });
        return itemList;
    }  
   
    public List<LookupItem> room(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Ward.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getWardNo()+" "+data.getWardType().getWardName());
                itemList.add(item);
            });
        return itemList;
    }  
    
    public List<LookupItem> lab(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Lab.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getUnitNo()+" "+data.getUnitName());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> testType(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(TestType.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getTestTypeName());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> patient(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Patient.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getFullname() +"-"+data.getOpdNumber());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> labTest(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(LabTest.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(DateUtil.parseLocalDateString(data.getTestDate(), Pattern._ddMMyyyy)+"-"+data.getRefNo());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> frequency(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Frequency.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getFrequencyName());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> drReport(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(DrReport.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getAssignPatient().getLabel() +" "+data.getRefNo());
                itemList.add(item);
            });
        return itemList;
    }  
    public List<LookupItem> stock(){
        List<LookupItem> itemList = new LinkedList<>();
        builder.findAll(Inventory.class).forEach(data ->{
            LookupItem item = new LookupItem();
                item.setId(data.getId());
                item.setItemName(data.getProductName());
                itemList.add(item);
            });
        return itemList;
    }  
}
