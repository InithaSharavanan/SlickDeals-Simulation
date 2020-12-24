
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Trang
 */
public class Thread {
    private String threadId;
    private String threadTitle;
    private String category;
    private String content;
    private String author;
    private String forumId;
    private int rating;
    private int view;
    private String status;
    private float price;
    private String dateAndTime;

    DataStorage data=new SQL_Database();
    private String userID;
    private ArrayList<Thread> deals = new ArrayList<Thread>();
    
    public Thread(){
        
    }
    public Thread(String id){
        this.userID=id;
    }
    public Thread(String threadId, String threadTitle, String category, String content, String author, String forumId, int rating,int view, String status, float price, String dateAndTime) {
        this.threadId = threadId;
        this.threadTitle = threadTitle;
        this.category = category;
        this.content = content;
        this.author = author;
        this.forumId = forumId;
        this.rating = rating;
        this.view = view;
        this.status = status;
        this.price = price;
        this.dateAndTime = dateAndTime;
    }

    public Thread(String threadId, String threadTitle, String category, String content,float price,String id, String selectedForumtoView) {
        this.threadId = threadId;
        this.threadTitle = threadTitle;
        this.category = category;
        this.content = content;
        this.userID=id;
        this.price = price;
        this.forumId=selectedForumtoView;
        
    }


    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
      public ArrayList<Thread> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<Thread> deals) {
        this.deals = deals;
    }
     public ArrayList<Thread> displayAllThread(){
        deals=data.getAllThread();
        return deals;
    }

    public String createThread()
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
        // String form=new FrontPageDeals().getSelectedForumtoView();
        String fileName =data.createNewThread(threadId,threadTitle,category,content,userID,price,forumId);
        
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
