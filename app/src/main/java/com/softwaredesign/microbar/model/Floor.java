package com.softwaredesign.microbar.model;

/**
 * Created by mac on 16/6/3.
 */
public class Floor {
    private String portraitUrl;
    private String username;
    private String timestamp;
    private String content;

    public Floor() {
    }

    public Floor(String portraitUrl, String username, String timestamp, String content) {
        this.portraitUrl = portraitUrl;
        this.username = username;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
