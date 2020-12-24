/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Roshan
 */
@ManagedBean
@RequestScoped
public class SignUp {

    /**
     * Creates a new instance of signUp
     */
    private String id;
    private String password;
    private String name;
    private String dateAndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 
    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
     public String signUp()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
       
        DataStorage data = new SQL_Database(); 
        
        String fileName =data.signUp(id,password, name);
        
        if(fileName.equals("ConfirmSignUp"))
        {
            return "ConfirmSignUp";
        }
        else
        {
            return fileName;
        }
         
    }

    
}
