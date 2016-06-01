package com.hyj.entity;

import java.util.Date;

public class Post {
    private Integer id;

    private Integer creatorId;

    private String title;

    private Integer tag;

    private Date createTime;

    private Date modifyTime;

    public Post(Integer creatorId, String title, Integer tag, Date createTime, Date modifyTime) {
        this.creatorId = creatorId;
        this.title = title;
        this.tag = tag;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}