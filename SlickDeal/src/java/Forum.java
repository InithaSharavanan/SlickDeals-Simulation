/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Trang
 */
public class Forum {
    private String forumId;
    private String forumTitle;
    private String forumDesc;

    public Forum(String forumId, String forumTitle, String forumDesc) {
        this.forumId = forumId;
        this.forumTitle = forumTitle;
        this.forumDesc = forumDesc;
    }

   
    
    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }

    
}
