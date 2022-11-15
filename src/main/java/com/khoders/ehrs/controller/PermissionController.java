/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;

import com.khoders.ehrs.entity.AppPage;
import com.khoders.ehrs.entity.UserPage;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.listener.AppSession;
import com.khoders.ehrs.services.PermissionService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.Msg;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Richard Narh
 */
@Named(value = "permissionController")
@SessionScoped
public class PermissionController implements Serializable
{
    @Inject private CrudApi crudApi;
    @Inject private PermissionService permissionService;
    
    private UserPage userPage = null;
    private List<UserPage> userPageList = new LinkedList<>();
    private UserAccount selectedUser = null;
    private List<AppPage> appPageList = new LinkedList<>();
    
    @PostConstruct
    private void init(){
        appPageList = permissionService.appPageList();
    }
    
    public void addPageToUser(AppPage appPage)
    {
        if(selectedUser == null){
            Msg.error("Please select a user");
            return;
        }
        UserPage page = permissionService.userPageExist(selectedUser, appPage);
        if(page != null){
            Msg.error("The "+appPage.getPageName()+" page is already added for "+selectedUser.getEmailAddress());
            return;
        }
        userPage = new UserPage();
        userPage.setAppPage(appPage);
        userPage.genCode();
        userPage.setUserAccount(selectedUser);

        if(crudApi.save(userPage) != null){
           userPageList = CollectionList.washList(userPageList, userPage);
           Msg.info("Page is added to user");
        }
    }
    public void saveAllUserPage(){
        if(!userPageList.isEmpty()){
            for (UserPage page : userPageList) {
                page.setUserActivePage(page.isUserActivePage());
                crudApi.save(page);
            }
            Msg.info("User page saved successfully!");
        }
    }
    public void selectUser(){
        if(selectedUser == null) return;
        userPageList = permissionService.getUserPageList(selectedUser);
    }

    public void deleteUserPage(UserPage userPage){
        try
        {
            if (crudApi.delete(userPage))
            {
                userPageList.remove(userPage);
                Msg.info(Msg.SUCCESS_MESSAGE);
            }
        } catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    public UserAccount getSelectedUser()
    {
        return selectedUser;
    }

    public void setSelectedUser(UserAccount selectedUser)
    {
        this.selectedUser = selectedUser;
    }

    public List<AppPage> getAppPageList()
    {
        return appPageList;
    }

    public List<UserPage> getUserPageList()
    {
        return userPageList;
    }
}
