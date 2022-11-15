/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.AppModule;
import com.khoders.ehrs.entity.AppPage;
import com.khoders.ehrs.entity.UserPage;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.entity.settings.Department;
import com.khoders.ehrs.entity.settings.JobRole;
import com.khoders.resource.enums.Status;
import com.khoders.resource.jpa.CrudApi;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author richa
 */
@Stateless
public class PermissionService
{
    @Inject private CrudApi crudApi;

    public List<UserPage> getUserPageList(UserAccount userAccount)
    {
        return crudApi.getEm().createQuery("SELECT e FROM UserPage e WHERE e.userAccount=:userAccount ORDER BY e.appPage.appModule DESC", UserPage.class)
                .setParameter("userAccount", userAccount)
                .getResultList();
    }
    public List<AppPage> appPageList()
    {
       return crudApi.getEm().createQuery("SELECT e FROM AppPage e WHERE e.pageStatus=:pageStatus ORDER BY e.reorder ASC", AppPage.class)
               .setParameter("pageStatus", Status.ACTIVE)
               .getResultList();  
    }
    
    public List<AppPage> getAppPageList()
    {
       return crudApi.getEm().createQuery("SELECT e FROM AppPage e ORDER BY e.reorder ASC", AppPage.class).getResultList();  
    }
    public UserPage userPageExist(UserAccount selectedUser, AppPage appPage){
        return crudApi.getEm().createQuery("SELECT e FROM UserPage e WHERE e.userAccount=:selectedUser AND e.appPage=:appPage", UserPage.class)
                .setParameter("selectedUser", selectedUser)
                .setParameter("appPage", appPage)
                .getResultStream().findFirst().orElse(null);
    }

    public List<UserAccount> getUserAccountList()
    {
        try
        {
            return crudApi.getEm().createQuery("SELECT e FROM UserAccount e", UserAccount.class).getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Department> getDepartmentList()
    {
        try
        {
            return crudApi.getEm().createQuery("SELECT e FROM Department e", Department.class).getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    public List<JobRole> getJobRoleList()
    {
        try
        {
            return crudApi.getEm().createQuery("SELECT e FROM JobRole e", JobRole.class).getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    public List<AppModule> getAppModuleList(){
        try
        {
            return crudApi.getEm().createQuery("SELECT e FROM AppModule e", AppModule.class).getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Object[]> getPage(UserAccount userAccount){
        String qryString = "SELECT am.`id`, am.`module_name`, app.`id`, app.`page_name`, page.`user_active_page` FROM `user_pages` page\n" +
                            "INNER JOIN `app_page` app ON app.id = page.`app_page`\n" +
                            "INNER JOIN `app_module` am ON am.`id` = app.`app_module`\n" +
                            "WHERE page.`user_active_page`=TRUE\n" +
                            "ORDER BY am.`module_name` ASC";
         Query query = crudApi.getEm().createNativeQuery(qryString);
//         query.setParameter(1, userAccount);
         List<Object[]> objects =  query.getResultList();
         return objects; 
    }


}
