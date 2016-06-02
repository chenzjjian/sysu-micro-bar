package com.hyj.entity;

public class HistoryMessage {
    private Integer id;

    private Account account;

    private Floor floor;

    private Boolean isChecked;

    public HistoryMessage() {
    }

    public HistoryMessage(Account account, Floor floor, Boolean isChecked) {
        this.account = account;
        this.floor = floor;
        this.isChecked = isChecked;
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
}