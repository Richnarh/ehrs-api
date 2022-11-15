/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.config;
import com.khoders.ehrs.entity.AppPage;
import com.khoders.ehrs.entity.UserPage;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.payload.AuthRequest;
import com.khoders.ehrs.services.AuthService;
import com.khoders.ehrs.services.PermissionService;
import com.khoders.resource.jpa.CrudApi;
import static com.khoders.resource.utilities.SecurityUtil.hashText;
import com.khoders.resource.enums.AccessLevel;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
/**
 *
 * @author Pascal
 */
@Singleton
@Startup
public class AppInit {
    @Inject private CrudApi crudApi;
    @Inject private AuthService authService;
    @Inject private PermissionService permissionService;
    private List<AppPage> appPageList = new LinkedList<>();
    private AuthRequest authRequest = new AuthRequest();

    @PostConstruct
    public void init()
    {
        System.out.println("******************************************");
        System.out.println("******************************************");

        System.out.println("application started at - " + LocalDateTime.now());
        System.out.println("****  Going to create default data *******");

        System.out.println("******************************************");
        System.out.println("******************************************");

        createUser();
    }
    
    public void createUser(){
        try
        {
            String defaultUser = "ehrs";
            authRequest.setEmailAddress(defaultUser);
            authRequest.setPassword(defaultUser);
            UserAccount userAccount = authService.login(authRequest);
            if (userAccount != null){
                return;
            }

            userAccount = new UserAccount();
            userAccount.setAccessLevel(AccessLevel.SUPER_USER);
            userAccount.setEmailAddress(defaultUser);
            userAccount.setPassword(hashText(defaultUser));

            crudApi.save(userAccount);
            initPermission(userAccount);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void initPermission(UserAccount userAccount){
        appPageList = permissionService.getAppPageList();
        appPageList.forEach(appPage ->{
            initDefaultPerm(appPage, userAccount);
        });
    }
    
    private UserPage initDefaultPerm(AppPage appPage, UserAccount userAccount){
        UserPage userPage = permissionService.userPageExist(userAccount, appPage);
        if(userPage == null){
            userPage = new UserPage();
            userPage.setUserAccount(userAccount);
            userPage.setAppPage(appPage);
            userPage.genCode();
            userPage.setLastModifiedBy(userAccount.getEmailAddress());
            userPage.setLastModifiedDate(LocalDateTime.now());
            crudApi.save(userPage);
        }
    return userPage;
   }
}
