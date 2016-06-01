package com.hyj.entity;

import java.util.Date;

public class Floor {
    private Integer id;

    private Integer postId;

    private Integer accountId;

    private Integer replyFloorId;

    private Boolean isReply;

    private Integer levelNum;

    private Date createTime;

    private String detail;

    public Floor() {}

    public Floor(Integer postId, Integer accountId, Integer replyFloorId, Boolean isReply,  Date createTime, String detail) {
        this.postId = postId;
        this.accountId = accountId;
        this.replyFloorId = replyFloorId;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getReplyFloorId() {
        return replyFloorId;
    }

    public void setReplyFloorId(Integer replyFloorId) {
        this.replyFloorId = replyFloorId;
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