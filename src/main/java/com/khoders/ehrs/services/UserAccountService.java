/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.resource.jpa.CrudApi;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Khoders
 */
@Stateless
public class UserAccountService
{
    @Inject private CrudApi crudApi;
    
   
    public boolean isTaken(String emailAddress)
    {
        String qryString = "SELECT e FROM UserAccount e WHERE e.emailAddress=?1";
        try {
            UserAccount account = crudApi.getEm().createQuery(qryString, UserAccount.class)
                    .setParameter(1, emailAddress)
                    .getSingleResult();
            
            return account != null;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false;
    }
    



}
