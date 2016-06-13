package com.softwaredesign.microbar.model;


/**
 * Created by lenovo on 2016/6/3.
 */
public class mainObject {

    private int commentNum;
    private String createTime;
    private int postId;
    private String tag;
    private String title;

    //public static List<mainObject> myList = new ArrayList<mainObject>();

    /*
    mainObject(int cN, String cT, int pI, String ta, String t) {
        postId = pI;
        title = t;
        commentNum = cN;
        createTime = cT;
        tag = ta;
    }
    */


    public String getTitle() {
        return title;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getTag() {
        return tag;
    }

    public int getPostId() {
        return postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "mainObject{" +
                "title='" + title + '\'' +
                ", commentNum=" + commentNum +
                ", createTime='" + createTime + '\'' +
                ", tag='" + tag + '\'' +
                ", postId=" + postId +
                '}';
    }

}
