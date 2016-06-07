package com.hyj.entity;

public class HistoryMessage {
    private Integer id;

    private Account account;

    private Floor floor;

    private Boolean isChecked;

    private Boolean isComment;


    public HistoryMessage() {
    }

    public HistoryMessage(Account account, Floor floor, Boolean isChecked, Boolean isComment) {
        this.account = account;
        this.floor = floor;
        this.isChecked = isChecked;
        this.isComment = isComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }
}