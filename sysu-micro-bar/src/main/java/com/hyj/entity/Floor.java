package com.hyj.entity;

import java.util.Date;

public class Floor {
    private Integer id;

    private Post post;

    private Account account;

    private Floor replyFloor;

    private Boolean isReply;

    private Integer levelNum;

    private Date createTime;

    private String detail;

    public Floor() {}

    public Floor(Post post, Account account, Floor replyFloor, Boolean isReply,  Date createTime, String detail) {
        this.post = post;
        this.account = account;
        this.replyFloor = replyFloor;
        this.isReply = isReply;
        this.createTime = createTime;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Floor getReplyFloor() {
        return replyFloor;
    }

    public void setReplyFloor(Floor replyFloor) {
        this.replyFloor = replyFloor;
    }

    public Boolean getIsReply() {
        return isReply;
    }

    public void setIsReply(Boolean isReply) {
        this.isReply = isReply;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}