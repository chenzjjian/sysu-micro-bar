package com.softwaredesign.microbar.model;

import java.io.Serializable;

public class Post implements Serializable{
    private String title;
    private int postId;
    private int commentNum;
    private String createTime;
    private String tag;

    public Post() {
    }

    public Post(String title, int postId, int commentNum, String createTime, String tag) {
        this.title = title;
        this.postId = postId;
        this.commentNum = commentNum;
        this.createTime = createTime;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", postId=" + postId +
                ", commentNum=" + commentNum +
                ", createTime='" + createTime + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
