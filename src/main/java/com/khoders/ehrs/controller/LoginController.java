/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;

import com.khoders.ehrs.Pages;
import com.khoders.ehrs.entity.administration.UserAccount;
import com.khoders.ehrs.listener.AppSession;
import com.khoders.ehrs.payload.AuthRequest;
import com.khoders.ehrs.services.AuthService;
import com.khoders.resource.enums.AccessLevel;
import com.khoders.resource.utilities.Msg;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;

/**
 *
 * @author richa
 */
@RequestScoped
@Named(value = "loginController")
public class LoginController implements Serializable
{
    @Inject private AppSession appSession;
    @Inject private AuthService authService;
    
    private String userEmail;
    private String password;
    
    private AuthRequest authRequest = new AuthRequest();
    
    public String doLogin()
    {
        try
        {
            authRequest.setEmailAddress(userEmail);
            authRequest.setPassword(password);

            UserAccount account = authService.login(authRequest);

            if (account == null)
            {
              Msg.error("Wrong username or Password");
                return null;
            }
           if(account.getAccessLevel() != AccessLevel.SUPER_USER)
           {
              Msg.error("Access Denied!!");
                return null;
           }
               
            initLogin(account);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    public String initLogin(UserAccount userAccount)
    {
        try
        {

            if (userAccount == null)
            {
                Msg.error("Wrong username or Password");
                return null;
            }

            appSession.login(userAccount);
            
            Faces.redirect(Pages.index);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    public String doLogout()
    {
        try
        {
            Faces.invalidateSession();
            Faces.logout();
            
            Faces.redirect(Pages.login);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    } 
}
