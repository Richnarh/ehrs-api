/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.mapper;

import com.khoders.ehrs.entity.AppModule;
import com.khoders.ehrs.entity.UserPage;
import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.payload.AppModuleDto;
import com.khoders.ehrs.payload.AuthResponse;
import com.khoders.ehrs.payload.UserAccountDto;
import com.khoders.ehrs.payload.UserPageDto;
import com.khoders.ehrs.services.PermissionService;
import com.khoders.resource.enums.AccessLevel;
import com.khoders.resource.enums.Title;
import static com.khoders.resource.utilities.SecurityUtil.hashText;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.Pattern;
import com.khoders.resource.utilities.Stringz;
import com.khoders.resource.utilities.SystemUtils;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author pascal
 */
public class AuthMapper
{
    @Inject private CrudApi crudApi;
    @Inject private PermissionService permissionService;
    
    public UserAccount toEntity(UserAccountDto dto){
        UserAccount userAccount = new UserAccount();
         if (dto.getId() != null){
            userAccount.setId(dto.getId());
        }
        userAccount.setEmailAddress(dto.getEmailAddress());
        userAccount.setPhoneNumber(dto.getPhoneNumber());
        userAccount.setTitle(Title.valueOf(dto.getTitle()));
        userAccount.setAccessLevel(AccessLevel.SUPER_USER);
        userAccount.setPassword(hashText(dto.getPassword()));
        
        return userAccount;
    }

    public AuthResponse toDto(UserAccount userAccount)
    {
        System.out.println("creating response...");
       AuthResponse dto = new AuthResponse();
       Employee emp = get(userAccount);
       System.out.println("Data: "+SystemUtils.KJson().toJson(userAccount));
       if(userAccount.getId() == null) return null;
        System.out.println("Here.....");
       dto.setEmailAddress(userAccount.getEmailAddress());
       dto.setSessionId(userAccount.getId());
       dto.setId(userAccount.getId());
       if(emp != null){
           dto.setEmployee(emp.getFirstname() +" "+emp.getLastname());
           dto.setEmployeeId(emp.getId());
       }
//       dto.setAppModuleList(userModuleData(userAccount));
       dto.setValueDate(DateUtil.parseLocalDateString(userAccount.getValueDate(), Pattern._ddMMyyyy));
       
        System.out.println("Data: "+SystemUtils.KJson().toJson(dto));

       return dto;
    }
    
    private Employee get(UserAccount userAccount){
        return crudApi.getEm().createQuery("SELECT e FROM Employee e WHERE e.userAccount=:userAccount", Employee.class)
                .setParameter("userAccount", userAccount)
                .getResultStream().findFirst().orElse(null);
    }

    private List<AppModuleDto> userModuleData(UserAccount userAccount){
        List<Object[]> userPages = permissionService.getPage(userAccount);
        List<AppModuleDto> moduleAppList = new LinkedList<>();
        List<AppModule> moduleList = permissionService.getAppModuleList();
        
        List<UserPageDto> pageList = new LinkedList<>();
        
//        List<UserPage> userPageList = permissionService.getPages(userAccount, appModule);
        
//        for (UserPage userPage : userPageList) {
//            AppModuleDto amd = new AppModuleDto();
//            UserPageDto page = new UserPageDto();
//            if(userPage.getAppPage() != null && userPage.getAppPage().getAppModule() != null){
//                amd.setModuleId(userPage.getAppPage().getAppModule().getId());
//                amd.setModuleName(userPage.getAppPage().getAppModule().getModuleName());
//            }
//            page.setPageId(userPage.getId());
//            page.setPageName(userPage.getAppPage() != null ? userPage.getAppPage().getPageName() : "");
//            page.setUserActivePage(userPage.isUserActivePage());
//
//            pageList.add(page);
//
//            amd.setUserPageData(pageList);
//            moduleAppList.add(amd);
//        }
            
        return moduleAppList;
    }
}
