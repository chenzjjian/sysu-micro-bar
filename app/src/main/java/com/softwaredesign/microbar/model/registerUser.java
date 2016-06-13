package com.softwaredesign.microbar.model;


/**
 * Created by lenovo on 2016/5/26.
 */
public class registerUser {
    private String nickname;
    private String password;
    private String stuNo;

    public registerUser(String account, String pwd, String id) {
        nickname = account;
        password = pwd;
        stuNo = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
