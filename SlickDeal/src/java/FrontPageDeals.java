
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public class FrontPageDeals {

    /**
     * Creates a new instance of FrontPageDeals
     */
    private DataStorage data;
    private String id;
    private String password;
    private String selectedForumtoView;
    private String selectedThreadtoView;
    private String selectedStoretoView;
    private String selectedtoView;
    private String threadId;
    private String threadTitle;
    private String category;
    private String content;
    private float price;
    private String image;
    private String imageCat;
    private String replyMessage;
    private int viewCount;
    private int replyCount;
    private int rating;
    private int notificationCount;
    private int alertCount;
    private int totCount;
    private String dealItem;
    private String ratingMessage;
    private String selecteddealItem;
    private String rankingType;
    
    private ArrayList<Forum> forums
            = new ArrayList<Forum>();
    private ArrayList<Store> stores
            = new ArrayList<Store>();
    private ArrayList<Thread> deals
            = new ArrayList<Thread>();
    private ArrayList<ThreadDetails> threadDeatails
            = new ArrayList<ThreadDetails>();
    private ArrayList<String> storeNames=new ArrayList<>();
    private ArrayList<String> dealAlerts=new ArrayList<>();
    
       
     public FrontPageDeals(){
         
     }
    public FrontPageDeals(String id, String password) {
        this.id = id;
        this.password = password;
    }

    
    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }

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

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSelectedForumtoView() {
        return selectedForumtoView;
    }

    public void setSelectedForumtoView(String selectedForumtoView) {
        this.selectedForumtoView = selectedForumtoView;
    }

    public String getSelectedThreadtoView() {
        return selectedThreadtoView;
    }

    public void setSelectedThreadtoView(String selectedThreadtoView) {
        this.selectedThreadtoView = selectedThreadtoView;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ThreadDetails> getThreadDeatails() {
        return threadDeatails;
    }

    public void setThreadDeatails(ArrayList<ThreadDetails> threadDeatails) {
        this.threadDeatails = threadDeatails;
    }

    public ArrayList<Forum> getForums() {
        return forums;
    }

    public void setForums(ArrayList<Forum> forums) {
        this.forums = forums;
    }
    public ArrayList<Thread> getDeals() {
        return deals;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }

    public void setDeals(ArrayList<Thread> deals) {
        this.deals = deals;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRankingType() {
        return rankingType;
    }

    public void setRankingType(String rankingType) {
        this.rankingType = rankingType;
    }
    
     public ArrayList<Forum> displayAllforum(){
       return forums; 
    }

    public String getDealItem() {
        return dealItem;
    }

    public void setDealItem(String dealItem) {
        this.dealItem = dealItem;
    }
     
    public void updateForums()
    {
        forums = data.getForum();
    }
    public void updateDeals()
    {
        deals = data.getAllThreadWithRating2();
    }

    public String getRatingMessage() {
        return ratingMessage;
    }

    public void setRatingMessage(String ratingMessage) {
        this.ratingMessage = ratingMessage;
    }

    public int getTotCount() {
        return totCount;
    }

    public void setTotCount(int totCount) {
        this.totCount = totCount;
    }
    
    public void updateStoreNames()
    {
        storeNames = data.getStores();
    }
    public ArrayList<Store> displayAllStores(){
        return stores;
    }
    public ArrayList<String> displayAllStoreNames(){
        return storeNames;
    }
    public ArrayList<Thread> displayAllThreadWithRating2(){
        return deals;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

     public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml"; 
    }

    public String getImageCat() {
        return imageCat;
    }

    public void setImageCat(String imageCat) {
        this.imageCat = imageCat;
    }
     
    public String updateSelectedForum(String forumtitle){
        
        selectedForumtoView=forumtitle;
        deals=data.getForumThreads(forumtitle);
        return "forumDisplay.xhtml";
    }
    public String updateSelectedThread(String threadtitle){
        
        selectedThreadtoView=threadtitle;
        String tId=data.getThreadId(selectedThreadtoView);
        data.updateViewCount(tId);
        rankingType=data.getRatingType(id, tId);
     //   deals=data.getForumThreads(threadtitle);
        return "threadDetails.xhtml";
    }
    public String updateSelectedThreadToView(String threadtitle){
        
        selectedThreadtoView=threadtitle;
        String tId=data.getThreadId(selectedThreadtoView);
        rankingType=data.getRatingType(id, tId);
        data.updateViewCount(tId);
     //   deals=data.getForumThreads(threadtitle);
        return "viewThreadDetails.xhtml";
    }
     public String updateSelectedStore(String storeName){
        
        selectedStoretoView=storeName;
        stores=data.getStoreDetails(storeName);
     //   deals=data.getForumThreads(threadtitle);
        return "storeDetails.xhtml";
    }
    public ArrayList<Thread> displayForumThreads(){
       // String forumId=data.getForumId(selectedForumtoView); 
        deals=data.getForumThreads(selectedForumtoView);
        return deals;
    }
    public String createNewThread(){
        if(selectedForumtoView==null){
            selectedForumtoView="Hot Deals";
        }
        Thread thread=new Thread(threadId,threadTitle,category,content,price,id,selectedForumtoView);
        String fileName=thread.createThread();
        return fileName;
    }
     public ArrayList<Thread> displayThreadDetails(String threadtitle){
         selectedThreadtoView=threadtitle;
         return displayThreadDetails();
     }
    public ArrayList<Thread> displayThreadDetails(){
        ArrayList<Thread> threadDetails=new ArrayList<>();
        String tId=data.getThreadId(selectedThreadtoView);
         rankingType=data.getRatingType(id,tId);
        replyCount=data.getReplyCount(tId);
       // rating=data.getRatingCount(threadId);
        threadDetails=data.getThreadDetail(selectedThreadtoView);
        for(int i=0;i<threadDetails.size();i++){
            imageCat=threadDetails.get(i).getCategory();
        }
        String image=getImagePath(imageCat);
        return threadDetails;
    }
   
    public String getImagePath(String category){
        
        if(category.equals("Laptop")){
            image="images/laptop.png";
        }
        else if(category.equals("Card")){
            image="images/card.png";
        }
        else if(category.equals("Airpod")){
            image="images/airpod.jpg";
        }
        else if(category.equals("TV")){
            image="images/tv.jpg";
        }
        else if(category.equals("Sofa")){
            image="images/sofa.jpg";
        }
        else if(category.equals("Computer")){
            image="images/computer.jpg";
        }
        else if(category.equals("Coffee")){
            image="images/coffee.jpg";
        }
        else if(category.equals("Speaker")){
            image="images/speaker.jpg";
        }
        else if(category.equals("Glass")){
            image="images/glass.jpeg";
        }
        else if(category.equals("Watch"))
        {
            image="images/watches.jpg";
        }
        else if(category.equals("Washing Machine"))
        {
            image="images/washingMachine.png";
        }
          else if(category.equals("Chair"))
        {
            image="images/chair.jpg";
        }
        else if(category.equals("Play Station"))
        {
            image="images/playstation.jpg";
        }
         else if(category.equals("Kids"))
        {
            image="images/buildingBlocks.jpg";
        }
         else if(category.equals("Amazon"))
        {
            image="images/amazon.png";
        }
         else if(category.equals("Other"))
        {
            image="images/Other.jpg";
        }
        return image;
    }
    public String createNewReply(){
        String tId=data.getThreadId(selectedThreadtoView);
        ThreadDetails threadDet=new ThreadDetails(tId,replyMessage,id,DateAndTime.DateTime());
        String fileName=threadDet.createNewReply();
        return fileName;
    }
    public ArrayList<ThreadDetails> displayThreadReplies(){
        String tId=data.getThreadId(selectedThreadtoView);
        ArrayList<ThreadDetails> replies=data.getThreadReplies(tId);
        return replies;
    }
    
    public String incrementRating(){
        String tId=data.getThreadId(selectedThreadtoView);
         rankingType=data.getRatingType(id,tId);
        if(rankingType.equals("like")){
            ratingMessage="You have already voted this up";
        }
        else{
            if(rankingType.equals("dislike")){
                //data.decrementRating(tId);
                data.deleteRatingType(id,tId);
            }
            rankingType="like";
            data.incrementRating(tId);
            data.addRatingType(id,tId,"like");
            displayThreadDetails();
            
            String status=data.getNotificationStatus(tId);   
            if(status.equals("New")){
                rating=data.getRatingCount(tId);
                if(rating>=2){
                    data.updateStatusToReview(tId);
                }
            }
        }
        
       return "threadDetails.xhtml";
    }
     public String decrementRating(){
        String tId=data.getThreadId(selectedThreadtoView);
        rankingType=data.getRatingType(id,tId);
        if(rankingType.equals("dislike")){
            ratingMessage="You have already voted this down";
        }
        else{
            if(rankingType.equals("like")){
              //  data.decrementRating(tId);
                data.deleteRatingType(id,tId);
            }
            rankingType="dislike";
        data.decrementRating(tId);
        data.addRatingType(id,tId,"dislike");
         displayThreadDetails();    
        }
        
         return "threadDetails.xhtml";
    }

    public int notifyEditor(){
        String accountType=data.getAccountType(id);
        if(accountType.equals("Editor")){
            notificationCount=data.anyNotification();
            if(notificationCount>0){
             totCount=alertCount+notificationCount;
                return notificationCount;
            }
        }
        
        return 0;
    }
    public int alertNotification(){
            
        if(alertCount>0){
            return alertCount;
        }
   
        return 0;
    }
    public ArrayList<Thread> displayPendingThreads(){
        ArrayList<Thread> pendingThreads=data.getPendingThreads();
        return pendingThreads;
    }
    public String updateSelectedThreadByEditor(String threadtitle){
        
        selectedThreadtoView=threadtitle;
        String tId=data.getThreadId(selectedThreadtoView);
        data.updateViewCount(tId);
     //   deals=data.getForumThreads(threadtitle);
        return "threadDetailsEditor.xhtml";
    }
    public String approveDealThread(String threadtitle){
        selectedThreadtoView=threadtitle;
        String tId=data.getThreadId(selectedThreadtoView);
        data.updateStatusToApprove(tId);
        int c=notifyEditor();
        if(c>0){
            return "approve.xhtml";
        }
        return "frontPageDeal.xhtml";
        
    }
     public String rejectDealThread(String threadtitle){
        selectedThreadtoView=threadtitle;
        String tId=data.getThreadId(selectedThreadtoView);
        data.updateStatusToReject(tId);
        int c=notifyEditor();
        if(c>0){
            return "reject.xhtml";
        }
        return "frontPageDeal.xhtml";
        
    }
     
    public ArrayList<Thread> displayApprovedThreads(){
        deals=data.getApprovedDeals();
        return deals;
    }
    public String createNewDealAlert(){
        String fileName=data.createNewDealAlert(id,dealItem);
        dealItem="";
        return fileName;
    }
    public ArrayList<String> displayDealAlerts(){
        dealAlerts=data.getDealAlerts(id);
        return dealAlerts;
    }
    public String updateSelectedDealAlert(String dealItem){
        
        selecteddealItem=dealItem;
        return "editDealAlert.xhtml";
    }
    public String updateDealAlertItem(){
        data.updateDealAlertItem(id,selecteddealItem,dealItem);
        dealItem="";
        return "dealAlerts.xhtml";
    }
    public String deleteDealAlertItem(String dealItem){
        data.deleteDealAlertItem(id,dealItem);
        
        return "dealAlerts.xhtml";
    }
    public ArrayList<String> dealAlertsNotification(){
        ArrayList<String> alertThreads=new ArrayList<>();
        ArrayList<String> alertItems= new ArrayList<>();
        ArrayList<String> dealThreads=new ArrayList<String>();
        alertItems= data.getDealAlerts(id);
        if(alertItems.size()>0){
            for(int i =0;i<alertItems.size();i++){
                dealThreads=data.getThreads(alertItems.get(i));
                for(int j=0;j<dealThreads.size();j++){
                    String d=dealThreads.get(j);
                    alertThreads.add(d);
                }  
            }
             alertCount=alertThreads.size();
            totCount=alertCount+notificationCount;
            System.out.println(alertThreads.get(0));
            System.out.println(alertThreads.get(1));
        }
       
      
       return alertThreads;
    }
    
}



