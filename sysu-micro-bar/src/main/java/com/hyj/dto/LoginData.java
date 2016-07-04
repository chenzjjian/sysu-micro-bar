package com.hyj.dto;

import com.hyj.entity.Account;
import com.hyj.util.StringUtil;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class LoginData {
    private Integer accountId;
    private String headImageUrl;
    private String nickname;


    private boolean loginStatus;
    private String message;

    public LoginData() {}

    public LoginData(Integer accountId, String headImageUrl, String nickname, boolean loginStatus, String message) {
        this.accountId = accountId;
        this.headImageUrl = headImageUrl;
        this.nickname = nickname;
        this.loginStatus = loginStatus;
        this.message = message;
    }

    public LoginData(Account account, boolean loginStatus, String message) {
        this.accountId =  account != null ? account.getId() : -1;
        this.headImageUrl = account != null ? account.getHeadImageUrl() : "";
        this.nickname = account != null ? account.getNickname() : StringUtil.getRandomString(6);
        this.loginStatus = loginStatus;
        this.message = message;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public String getMessage() {
        return message;
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

}
