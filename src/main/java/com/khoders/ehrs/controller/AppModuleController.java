package com.khoders.ehrs.controller;

import com.khoders.ehrs.entity.AppModule;
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
 * @author Pascal
 */
@Named(value = "appModuleController")
@SessionScoped
public class AppModuleController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private PermissionService permissionService;
    private AppModule appModule = new AppModule();
    private List<AppModule> appModuleList = new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    public void init(){
     clearModule();   
     appModuleList = permissionService.getAppModuleList();
    }
    
    public void saveAppModule(){
        try {
            if(crudApi.save(appModule) != null){
                appModuleList = CollectionList.washList(appModuleList, appModule);
                Msg.info("Module saved successfully!");
            }
            clearModule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editAppModule(AppModule appModule){
        this.appModule = appModule;
        optionText = "Update";
    }
    
    public void deleteAppModule(AppModule appModule){
        try {
            if(crudApi.delete(appModule)){
                appModuleList.remove(appModule);
                Msg.info("Module deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearModule(){
        appModule = new AppModule();
        optionText = "Save Changes";
    }

    public AppModule getAppModule() {
        return appModule;
    }

    public void setAppModule(AppModule appModule) {
        this.appModule = appModule;
    }

    public String getOptionText() {
        return optionText;
    }

    public List<AppModule> getAppModuleList() {
        return appModuleList;
    }
}
