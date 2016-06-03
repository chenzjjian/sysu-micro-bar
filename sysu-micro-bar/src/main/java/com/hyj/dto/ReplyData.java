package com.hyj.dto;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class ReplyData {
    private int postId;
    private String postTitle;
    private String who;
    private String createTime;

    public ReplyData() {
    }

    public ReplyData(int postId, String postTitle, String who, String createTime) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.who = who;
        this.createTime = createTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
