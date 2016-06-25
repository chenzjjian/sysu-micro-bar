package com.hyj.entity;

import java.util.Date;

public class Post {
    private Integer id;

    private Account creator;

    private String title;

    private Integer tag;

    private Date createTime;



    public Post(String title, Integer tag) {
        this.title = title;
        this.tag = tag;
    }

    public Post(Account creator, String title, Integer tag, Date createTime) {
        this.creator = creator;
        this.title = title;
        this.tag = tag;
        this.createTime = createTime;
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
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

}