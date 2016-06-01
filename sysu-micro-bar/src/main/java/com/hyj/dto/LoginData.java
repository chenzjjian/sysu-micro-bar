package com.hyj.dto;

import com.hyj.entity.Account;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class LoginData {
    private int accountId;
    private boolean loginStatus;
    private String message;

    public LoginData() {}

    public LoginData(Account account, boolean loginStatus, String message) {
        this.accountId =  account == null ? -1 : account.getId();
        this.loginStatus = loginStatus;
        this.message = message;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public String getMessage() {
        return message;
    }
}
