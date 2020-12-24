
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SQL_Database implements DataStorage{
   
    final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/annamalaishai70?useSSL=false";
    final String db_id="root";
    final String db_pwd="root123";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @Override
    public String login(String id, String password) {
        
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from useraccount where user_id = '" + id + "'");
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(3)))
                {
                    //password is good
                    return "frontPageDeal";
                    //go to the welcome page 
                }
                else
                {
                    //password is not correct
                    return "loginNotOK";
                     
                }
            }
            else
            {
                return "loginNotOK";
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Forum> getForum() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the forums to display in forum page
            resultSet = statement.executeQuery("Select * from forum");
            
            ArrayList<Forum> aList = new ArrayList<Forum>();
            
            while(resultSet.next())
            {
                 Forum forum = new Forum(resultSet.getString(1), 
                 resultSet.getString(2),resultSet.getString(3));
                 aList.add(forum);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String createNewThread(String id, String title, String category, String description, String author,float price,String forumId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into thread values('" + id + "','" + title + "','" + category + "','" + description + "','" + author + "','" + forumId + "',0,0,'New','" + price + "',now())");
            connection.commit();
            connection.setAutoCommit(true);
            return "ConfirmNewThread";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getAllThread() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String signUp(String id, String password, String name) {
         try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_pwd);
            //crate the statement
            statement = connection.createStatement();
            
            //do a query
            resultSet = statement.executeQuery("Select * from useraccount where user_id = '" + id + "'");
            
            if(resultSet.next())
            {
                return("SignUpNotOk");
            }
            else
            {
                //insert a record into useraccount
            int r = statement.executeUpdate("insert into useraccount values ('" + id + "', '" + name + "', '" + password + "','User',now())");
                return ("ConfirmSignUp");
                 
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return("SignUpNotOk");
        }
        finally
        {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
   

    }

    @Override
    public ArrayList<Store> getStoreDetails(String storeName) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the forums to display in forum page
            resultSet = statement.executeQuery("Select * from store where store_name='" + storeName + "'");
            
            ArrayList<Store> aList = new ArrayList<Store>();
            
            while(resultSet.next())
            {
                 Store store = new Store(resultSet.getString(1), 
                 resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5));
                 aList.add(store);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getAllThreadWithRating2() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread where rating>=2");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getForumThreads(String forumTitle) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread where forum_title = '" + forumTitle + "' order by date_time desc");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String getForumId(String forumTitle) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            String forumID="";
            resultSet = statement.executeQuery("Select forum_id from forum where forum_title = '" + forumTitle + "'");
            while(resultSet.next())
            {
               forumID=resultSet.getString(1);
            }
            return forumID;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getThreadDetail(String threadTitle) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread where thread_title='" + threadTitle + "'");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String createNewReply(String threadId, String reply, String userId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into threadreply values('" + threadId + "','" + reply + "','" + userId + "',now())");
            connection.commit();
            connection.setAutoCommit(true);
            return "threadDetails";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String getThreadId(String threadTitle) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            String threadId="";
            resultSet = statement.executeQuery("Select thread_id from thread where thread_title = '" + threadTitle + "'");
            while(resultSet.next())
            {
               threadId=resultSet.getString(1);
            }
            return threadId;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void updateViewCount(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set view=view+1 where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public int getReplyCount(String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select count(thread_id) from threadreply where thread_id='" + threadId + "'");
            if(resultSet.next())
            {
               return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<ThreadDetails> getThreadReplies(String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<ThreadDetails> aList = new ArrayList<ThreadDetails>();
            
            resultSet = statement.executeQuery("Select * from threadreply where thread_id='" + threadId + "'");
            while(resultSet.next())
            {
                ThreadDetails thread = new ThreadDetails(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void incrementRating(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set rating=rating+1 where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void decrementRating(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set rating=rating-1 where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public int getViewCount(String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select view from thread where thread_id='" + threadId + "'");
            if(resultSet.next())
            {
               return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public int getRatingCount(String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            
            resultSet = statement.executeQuery("Select rating from thread where thread_id='" + threadId + "'");
            if(resultSet.next())
            {
               return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String getAccountType(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            
            resultSet = statement.executeQuery("Select type from useraccount where user_id='" + id + "'");
            if(resultSet.next())
            {
               return resultSet.getString(1);
            }
            return null;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }

    }

    @Override
    public String getNotificationStatus(String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            
            resultSet = statement.executeQuery("Select status from thread where thread_id='" + threadId + "'");
            if(resultSet.next())
            {
               return resultSet.getString(1);
            }
            return null;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "null";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void updateStatusToReview(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set status='Under Review' where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public int anyNotification() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
            
            resultSet = statement.executeQuery("Select count(thread_id) from thread where status='Under Review'");
            if(resultSet.next())
            {
               return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getPendingThreads() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread where status='Under Review'");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void updateStatusToApprove(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set status='Approved' where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void updateStatusToReject(String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update thread set status='Rejected' where thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Thread> getApprovedDeals() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Thread> aList = new ArrayList<Thread>();
            
            resultSet = statement.executeQuery("Select * from thread where status='Approved'");
            while(resultSet.next())
            {
                Thread thread = new Thread(resultSet.getString(1), 
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getFloat(10),resultSet.getString(11));
                aList.add(thread);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<String> getStores() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the forums to display in forum page
            resultSet = statement.executeQuery("Select distinct(store_name) from store");
            
            ArrayList<String> aList = new ArrayList<String>();
            
            while(resultSet.next())
            {
                 aList.add(resultSet.getString(1));
                 
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String createNewDealAlert(String id, String dealItem) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into dealalerts values('" + id + "','" + dealItem + "')");
            connection.commit();
            connection.setAutoCommit(true);
            return "dealAlerts";
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<String> getDealAlerts(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the forums to display in forum page
            resultSet = statement.executeQuery("Select alert_item from dealalerts where user_id='" + id + "'");
            
            ArrayList<String> aList = new ArrayList<String>();
            
            while(resultSet.next())
            {
                 aList.add(resultSet.getString(1));
                 
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void updateDealAlertItem(String id, String oldDealItem, String newDealItem) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update dealalerts set alert_item='" + newDealItem + "' where alert_item='" + oldDealItem + "' and user_id='" + id + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void deleteDealAlertItem(String id, String dealItem) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("delete from dealalerts where alert_item='" + dealItem + "' and user_id='" + id + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void addRatingType(String id, String threadId,String ratingtype) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into userlikes values('" + id + "','" + threadId + "','" + ratingtype + "')");
            connection.commit();
            connection.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public String getRatingType(String id, String threadId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the forums to display in forum page
            resultSet = statement.executeQuery("Select ratingType from userlikes where user_id='" + id + "' and thread_id='" + threadId + "' ");
            
            
            if(resultSet.next())
            {
                 return resultSet.getString(1);
                 
            }
           
            return "";
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();  
            return "";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void deleteRatingType(String id, String threadId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("delete from userlikes where user_id='" + id + "' and thread_id='" + threadId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    

    @Override
    public ArrayList<String> getThreads(String dealItem) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_pwd);
            
            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<String> aList = new ArrayList<String>();
            
            resultSet = statement.executeQuery("Select distinct(thread_title) from thread where lower(thread_title) like lower('%" + dealItem + "%')");
            while(resultSet.next())
            {
               // Thread thread = new Thread(resultSet.getString(1));
                aList.add(resultSet.getString(1));
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    
    
}
