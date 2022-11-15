package com.khoders.ehrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author richa
 */
@Entity
@Table(name = "user_pages")
public class UserPage extends UserAccountRecord
{
    @JoinColumn(name = "app_page")
    @ManyToOne
    private AppPage appPage;
    
    @Column(name = "user_active_page")
    private boolean userActivePage;
    
    public AppPage getAppPage()
    {
        return appPage;
    }

    public void setAppPage(AppPage appPage)
    {
        this.appPage = appPage;
    }

    public boolean isUserActivePage() {
        return userActivePage;
    }

    public void setUserActivePage(boolean userActivePage) {
        this.userActivePage = userActivePage;
    }
}
