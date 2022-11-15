package com.khoders.ehrs.controller;

import com.khoders.ehrs.entity.administration.Employee;
import com.khoders.ehrs.services.UserService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private UserService userService;
    
    private Employee employee = new Employee();
    private List<Employee> employeeList = new LinkedList<>();
    private List<Employee> userPermissionsList = new LinkedList<>();
    private Employee selectedAccount=null;
    
    private String optionText;
    
    @PostConstruct
    private void init(){
        optionText = "Save Changes";
        employeeList = userService.getEmployeeList();
    }
    
    public void selectedAccountActn(Employee employee){
//        userPermissionsList = userService.getUserPermissionsList(employee);
        selectedAccount = employee;
    }
    
    public void saveUser()
    {
        if(selectedAccount == null)
        {
          Msg.info("Please select a user");
          return;
        }
        
        try
        {
           if(crudApi.save(selectedAccount)!= null){
            userPermissionsList = CollectionList.washList(userPermissionsList, selectedAccount);
            Msg.info("User permissions saved!");
           }
            clear();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void editPermissions(Employee selectedAccount)
    {
        this.selectedAccount = selectedAccount;
        optionText = "Update";
    }
    
    public void deletePermissions(Employee selectedAccount){
        try
        {
            if(crudApi.delete(selectedAccount))
            {
                userPermissionsList.remove(selectedAccount);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void saveEmployee()
    {
        try
        {
            if (crudApi.save(employee) != null)
            {
             employeeList = CollectionList.washList(employeeList, employee);
             Msg.info("User saved!");
            }
            clear();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
     
    public void editEmployee(Employee employee)
    {
        this.employee = employee;
        optionText = "Update";
    }
    
    public void deleteEmployee(Employee employee)
    {
        try
        {
            if(crudApi.delete(employee))
            {
              employeeList.remove(employee);
              Msg.info("User deleted!");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updatePassword()
    {
        if(employee.getEmailAddress() == null)
        {
          Msg.error("Please select a user");
          return;
        }
        try
        {
            Employee account = crudApi.find(Employee.class, employee.getId());
//            account.setPassword(hashText(employee.getPassword()));
            if(crudApi.save(account) != null)
            {
              Msg.info(employee.getEmailAddress()+"'s password is updated!");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void clear()
    {
        employee = new Employee();
        selectedAccount = null;
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList()
    {
        return employeeList;
    }

    public String getOptionText()
    {
        return optionText;
    }

    public void setOptionText(String optionText)
    {
        this.optionText = optionText;
    }

    public Employee getSelectedAccount()
    {
        return selectedAccount;
    }

    public void setSelectedAccount(Employee selectedAccount)
    {
        this.selectedAccount = selectedAccount;
    }

    public List<Employee> getUserPermissionsList()
    {
        return userPermissionsList;
    }
    
    
}
