/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Roshan
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{

    /**
     * Creates a new instance of login
     */
    private String id;
    private String password;
    private FrontPageDeals thefrontPageDeals;
    private Thread theThread;
    DataStorage data = new SQL_Database();

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

    public FrontPageDeals getThefrontPageDeals() {
        return thefrontPageDeals;
    }

    public void setThefrontPageDeals(FrontPageDeals thefrontPageDeals) {
        this.thefrontPageDeals = thefrontPageDeals;
    }

    public Thread getTheThread() {
        return theThread;
    }

    public void setTheThread(Thread theThread) {
        this.theThread = theThread;
    }
    
   public String login()
    {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        
        String accountType=data.getAccountType(id);
        
        String fileName = data.login(id, password);
        
        if(fileName.equals("frontPageDeal"))
        {
            thefrontPageDeals=new FrontPageDeals(id,password);
            thefrontPageDeals.setData(data);
            thefrontPageDeals.updateForums();
            theThread=new Thread(id);
            thefrontPageDeals.updateStoreNames();
            thefrontPageDeals.updateDeals();
            thefrontPageDeals.dealAlertsNotification();
            if(accountType.equals("Editor")){
                thefrontPageDeals.notifyEditor();
            }
            return "frontPageDeal";
        }
        else
        {
            return fileName;
        }
         
    }
    public String editorNotification(){
        String accountType=data.getAccountType(id);
        if(accountType.equals("Editor")){
            int c=thefrontPageDeals.notifyEditor();
            return "You have "+c+ " deals for approval";
        }
         return "";   
    }
  
}

