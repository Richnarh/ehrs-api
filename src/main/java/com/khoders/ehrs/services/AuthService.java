/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.services;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.mapper.AuthMapper;
import com.khoders.ehrs.payload.AuthRequest;
import com.khoders.ehrs.payload.AuthResponse;
import com.khoders.ehrs.payload.UserAccountDto;
import com.khoders.resource.jpa.CrudApi;
import static com.khoders.resource.utilities.SecurityUtil.hashText;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author richa
 */
@Stateless
public class AuthService
{
    @Inject private CrudApi crudApi;
    @Inject private AuthMapper authMapper;
     
     
    public AuthResponse register(UserAccountDto dto){
        UserAccount userAccount = authMapper.toEntity(dto);
        if(crudApi.save(userAccount) != null){
            Employee employee = new Employee();
            employee.setEmailAddress(userAccount.getEmailAddress());
            employee.setJobRole(userAccount.getJobRole());
            employee.setTitle(userAccount.getTitle());
            employee.setPhoneNumber(userAccount.getPhoneNumber());
            employee.setUserAccount(userAccount);
            employee.setFullname(userAccount.getEmailAddress());
            
            crudApi.save(employee);
            return authMapper.toDto(userAccount);
        }
        return null;
    }

    public AuthResponse doLogin(AuthRequest authRequest)
    {
        System.out.println("Entering...");
       UserAccount userAccount = login(authRequest);
       System.out.println("response from DB... "+userAccount);
       if(userAccount != null){
           return authMapper.toDto(userAccount);
        }
       return null;
    }
    
    public UserAccount login(AuthRequest authRequest){
        try
        {
            System.out.println("Logging from DB....");
            String qryString = "SELECT e FROM UserAccount e WHERE e.emailAddress=?1 AND e.password=?2";
            TypedQuery<UserAccount> typedQuery = crudApi.getEm().createQuery(qryString, UserAccount.class)
                    .setParameter(1, authRequest.getEmailAddress())
                    .setParameter(2, hashText(authRequest.getPassword()));
                    return typedQuery.getResultStream().findFirst().orElse(null);
                    
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
