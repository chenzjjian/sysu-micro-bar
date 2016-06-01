package com.hyj.entity;

public class HistoryMessage {
    private Integer id;

    private Integer accountId;

    private Integer floorId;

    private Boolean isChecked;

    public HistoryMessage() {
    }

    public HistoryMessage(Integer accountId, Integer floorId, Boolean isChecked) {
        this.accountId = accountId;
        this.floorId = floorId;
        this.isChecked = isChecked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}