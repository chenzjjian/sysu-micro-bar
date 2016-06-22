package com.hyj.dto;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class HistoryData {
    private int floorId;
    private String postTitle;
    private String who;
    private String createTime;

    public HistoryData() {
    }

    public HistoryData(int floorId, String postTitle, String who, String createTime) {
        this.floorId = floorId;
        this.postTitle = postTitle;
        this.who = who;
        this.createTime = createTime;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
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
