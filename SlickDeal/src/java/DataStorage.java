
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public interface DataStorage {
    String signUp(String id, String password,String name);
    String login(String id, String password);
    ArrayList<Forum> getForum();
    ArrayList<Store> getStoreDetails(String storeName);
    ArrayList<String> getStores();
    String createNewThread(String id, String title,String category,String description,String author,float price,String forumId);
    ArrayList<Thread> getAllThread();
    ArrayList<Thread> getForumThreads(String forumTitle);
    ArrayList<Thread> getAllThreadWithRating2();
    String getForumId(String forumTitle);
    ArrayList<Thread> getThreadDetail(String threadTitle);
    String createNewReply(String threadId,String reply,String userId);
    String getThreadId(String threadTitle);
    void updateViewCount(String threadId);
    int getReplyCount(String threadId);
    int getViewCount(String threadId);
    int getRatingCount(String threadId);
    void incrementRating(String threadId);
    void decrementRating(String threadId);
    void addRatingType(String id, String threadId,String ratingType);
    void deleteRatingType(String id, String threadId);
    ArrayList<ThreadDetails> getThreadReplies(String threadTitle);
    String getAccountType(String id);
    String getNotificationStatus(String threadId);
    void updateStatusToReview(String threadId);
    int anyNotification(); 
    ArrayList<Thread> getPendingThreads();
    void updateStatusToApprove(String threadId);
    void updateStatusToReject(String threadId);
    ArrayList<Thread> getApprovedDeals();
    String createNewDealAlert(String id,String dealItem);
    ArrayList<String> getDealAlerts(String id);
    void updateDealAlertItem(String id,String oldDealItem,String newDealItem);
    void deleteDealAlertItem(String id,String dealItem);
    String getRatingType(String id, String threadId);
    ArrayList<String> getThreads(String dealItem);
}
