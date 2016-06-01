package com.hyj.dto;

import com.hyj.entity.Account;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class RegisterData {
    private int accountId;
    private boolean registerStatus;
    private String message;
    public RegisterData() {}
    public RegisterData(Account account, boolean registerStatus, String message) {
        this.accountId = account == null ? -1 : account.getId();
        this.registerStatus = registerStatus;
        this.message = message;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean isRegisterStatus() {
        return registerStatus;
    }

    public String getMessage() {
        return message;
    }
}
