/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.resource.jpa.CrudApi;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author richa
 */
@Stateless
public class UserService
{
    @Inject private CrudApi crudApi;
    
    public List<UserAccount> getUserPermissionsList(UserAccount userAccount)
    {
        try
        {
            String qryString = "SELECT e FROM UserAccount e WHERE e.emailAddress=?1";
            return crudApi.getEm().createQuery(qryString, UserAccount.class).setParameter(1, userAccount.getEmailAddress()).getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
        
    public List<UserAccount> getAccountList(){
    try
    {
        return crudApi.getEm().createQuery("SELECT e FROM UserAccount e", UserAccount.class).getResultList();
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    return Collections.emptyList();
   }
    public List<Employee> getEmployeeList(){
    try
    {
        return crudApi.getEm().createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    return Collections.emptyList();
   }
    
    
}
