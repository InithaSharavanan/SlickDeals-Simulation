/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public class ThreadDetails {
    private String threadId;
    private String reply;
    private String userId;
    private String dateTime;
    DataStorage data = new SQL_Database();
    public ThreadDetails(String threadId,String reply, String userId, String dateTime) {
        this.threadId = threadId;
        this.reply = reply;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }


    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String createNewReply()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("internalError");
        }
       // frontPageDeals=new FrontPageDeals();
       //DataStorage data = new SQL_Database();

        //return data.createNewThread(threadId,threadTitle,category,content,userID, "F001");
         //String form=new FrontPageDeals().getSelectedForumtoView();
        String fileName =data.createNewReply(threadId,reply,userId);
        
        if(fileName.equals("ConfirmNewThread"))
        {
            return "ConfirmNewThread";
        }
        else
        {
            return fileName;
        }
    }   
    
            
}
