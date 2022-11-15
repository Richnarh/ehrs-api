/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.UserPage;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.lab.LabTest;
import com.khoders.ehrs.entity.patient.Patient;
import com.khoders.ehrs.mapper.LabTestMapper;
import com.khoders.ehrs.payload.AppModuleDto;
import com.khoders.ehrs.payload.LabTestDto;
import com.khoders.ehrs.payload.UserPageDto;
import com.khoders.ehrs.payload.patient.BillingDto;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.ParseValue;
import com.khoders.resource.utilities.Pattern;
import com.khoders.resource.utilities.Stringz;
import com.khoders.resource.utilities.SystemUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author richa
 */
@Stateless
public class SearchService
{
    @Inject private CrudApi crudApi;
    @Inject private LabTestMapper labTestMapper;
    @Inject private AppService appService;
    
    public List<LabTestDto> labTestData(String labTestDate, String opdSearchField){
        List<LabTestDto> dtoList = new LinkedList<>();
        
        if("null".equals(labTestDate)) labTestDate = null;
         
        Patient patient = findPatient(opdSearchField);
        Query query = null;
        String sql = "";
            if(labTestDate != null){
                 LocalDate localDate = LocalDate.parse(labTestDate, DateTimeFormatter.ofPattern(Pattern._yyyyMMdd));
                 sql = "SELECT e FROM LabTest e WHERE e.patient = :patient AND e.testDate = :testDate";
                 query = crudApi.getEm().createQuery(sql);
                 query.setParameter("testDate", localDate);
            }else{
                 sql = "SELECT e FROM LabTest e WHERE e.patient = :patient";
                 query = crudApi.getEm().createQuery(sql);
            }
            query.setParameter("patient", patient);
            
            List<LabTest> labTestList = query.getResultList();
            labTestList.forEach(labTest ->{
                dtoList.add(labTestMapper.toDto(labTest));
            });
        return dtoList;
    }
   
    @SuppressWarnings("null")
    public List<BillingDto> findBills(String opdSearchField){
        
        String queryString = "SELECT SUM(l.`price`) AS lab_bill, l.`id` AS lab_id, SUM(p.`price`) AS prescription_bill, p.`id` AS prescription_id, pa.`price` AS admission_bill, pa.`id` AS admission_id, pp.id AS patient_id, pp.full_name AS fullname \n" +
                        "FROM patient pp \n" +
                        "INNER JOIN `lab_result` l ON l.patient=pp.id \n" +
                        "INNER JOIN  `prescription` p ON p.patient=pp.id \n" +
                        "INNER JOIN `patient_admission` pa ON pa.patient=pp.id WHERE pp.`opd_number`=?1";
        Query query = crudApi.getEm().createNativeQuery(queryString);
        query.setParameter(1, opdSearchField);
        query.setMaxResults(1);
        List<Object[]> objects =  query.getResultList();
        
        List<BillingDto> dtos = new LinkedList<>();
        for (Object[] data : objects){
            BillingDto dto = new BillingDto();
            dto.setLabBill(data[0] != null ? ParseValue.parseDoubleValue(data[0]) : 0.0);
            dto.setLabBillId(Stringz.objectToString(data[1]));

            dto.setPrescriptionBill(data[2] != null ? ParseValue.parseDoubleValue(data[2]) : 0.0);
            dto.setPrescriptionBillId(Stringz.objectToString(data[3]));

            dto.setAdmissionBill(data[4] != null ? ParseValue.parseDoubleValue(data[4]) : 0.0);
            dto.setAdmissionBillId(Stringz.objectToString(data[5]));

            dto.setPatientId(Stringz.objectToString(data[6]));
            dto.setPatientName(Stringz.objectToString(data[7]));
            
            dtos.add(dto);
        }

        return dtos;
    }
    
    private Patient findPatient(String opdSearchField){
      return crudApi.getEm().createQuery("SELECT e FROM Patient e WHERE e.opdNumber = :opdSearchField", Patient.class)
                .setParameter("opdSearchField", opdSearchField)
                .getResultStream().findFirst().orElse(null);
        
    }
    public List<AppModuleDto> loadPages(String userAccountId, String appModule) {
        List<UserPage> userPageList = crudApi.getEm().createQuery("SELECT e FROM UserPage e WHERE e.userAccount=:userAccount AND e.appPage.appModule.moduleName=:appModule", UserPage.class)
                .setParameter("userAccount", appService.getUser(userAccountId))
//                .setParameter("userActivePage", true)
                .setParameter("appModule", appModule)
                .getResultList();
        
        List<UserPageDto> pageList = new LinkedList<>();
        List<AppModuleDto> moduleAppList = new LinkedList<>();
        for (UserPage userPage : userPageList) {
            AppModuleDto amd = new AppModuleDto();
            UserPageDto page = new UserPageDto();
            if(userPage.getAppPage() != null && userPage.getAppPage().getAppModule() != null){
                amd.setModuleId(userPage.getAppPage().getAppModule().getId());
                amd.setModuleName(userPage.getAppPage().getAppModule().getModuleName());
            }
            page.setPageId(userPage.getId());
            page.setPageName(userPage.getAppPage() != null ? userPage.getAppPage().getPageName() : "");
            page.setUserActivePage(userPage.isUserActivePage());

            pageList.add(page);

            amd.setUserPageData(pageList);
            moduleAppList.add(amd);
        }
        return moduleAppList;
    }
}
