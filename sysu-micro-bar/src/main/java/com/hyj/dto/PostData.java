package com.hyj.dto;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class PostData {
    private int postId;
    private String title;
    private String tag;
    private String createTime;
    private int commentNum;

    public PostData(int postId, String title, String tag, String createTime, int commentNum) {
        this.postId = postId;
        this.title = title;
        this.tag = tag;
        this.createTime = createTime;
        this.commentNum = commentNum;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
