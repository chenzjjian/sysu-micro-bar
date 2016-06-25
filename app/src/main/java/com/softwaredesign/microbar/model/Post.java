package com.softwaredesign.microbar.model;

public class Post {
    private String title;
    private int postId;
    private int commentNum;
    private String createTime;
    private String tag;

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
