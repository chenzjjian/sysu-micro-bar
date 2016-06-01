package com.hyj.dto;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class FloorData {
    String headImageUrl;
    String nickname;
    String createTime;
    String detail;
    boolean isReply;
    String replyWho;

    public FloorData() {}

    public FloorData(String headImageUrl, String nickname, String createTime, String detail, boolean isReply, String replyWho) {
        this.headImageUrl = headImageUrl;
        this.nickname = nickname;
        this.createTime = createTime;
        this.detail = detail;
        this.isReply = isReply;
        this.replyWho = replyWho;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public String getReplyWho() {
        return replyWho;
    }

    public void setReplyWho(String replyWho) {
        this.replyWho = replyWho;
    }
}
