/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.commons;

import com.khoders.ehrs.entity.AppModule;
import com.khoders.ehrs.entity.AppPage;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.ehrs.services.PermissionService;
import com.khoders.resource.enums.AccessLevel;
import com.khoders.resource.enums.IdType;
import com.khoders.resource.enums.Status;
import com.khoders.resource.enums.Title;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author richa
 */
@Named(value = "userCommons")
@SessionScoped
public class UserCommons implements Serializable
{
    @Inject private PermissionService permissionService;
    
    private List<UserAccount> userAccountList = new LinkedList<>();
    private List<AppPage> appPageList = new LinkedList<>();
    private List<Department> departmentList = new LinkedList<>();
    private List<JobRole> jobRoleList = new LinkedList<>();
    private List<AppModule> appModuleList = new LinkedList<>();
            
    @PostConstruct
    public void init(){
       userAccountList = permissionService.getUserAccountList();
       appPageList = permissionService.getAppPageList();
       departmentList = permissionService.getDepartmentList();
       jobRoleList = permissionService.getJobRoleList();
       appModuleList = permissionService.getAppModuleList();
    }
    
    public List<Status> getStatusList(){
        return Arrays.asList(Status.values());
    }
    public List<AccessLevel> getAccessLevelList(){
        return Arrays.asList(AccessLevel.values());
    }
    public List<IdType> getIdTypeList(){
        return Arrays.asList(IdType.values());
    }
    public List<Title> getTitleList(){
        return Arrays.asList(Title.values());
    }

    public List<UserAccount> getUserAccountList()
    {
        return userAccountList;
    }

    public List<AppPage> getAppPageList()
    {
        return appPageList;
    }

    public List<Department> getDepartmentList()
    {
        return departmentList;
    }

    public List<JobRole> getJobRoleList()
    {
        return jobRoleList;
    }

    public List<AppModule> getAppModuleList() {
        return appModuleList;
    }
    
}
