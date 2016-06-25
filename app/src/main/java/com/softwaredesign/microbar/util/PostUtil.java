package com.softwaredesign.microbar.util;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by mac on 16/6/23.
 */
public class PostUtil {
    private static final String BASE_URL = "http://119.29.178.68:8080/sysu-micro-bar/";

    // 查看更多帖子
    public static void lookForMore(String url, int postTotal, Callback callback) {
        OkHttpUtils
                .post()
                .url(getAbsoluteUrl(url))
                .addParams("currentPostNum", Integer.toString(postTotal))
                .build()
                .connTimeOut(20000)
                .execute(callback);
    }

    public static void refreshForNew(String url, int lastPostId, Callback callback) {
        OkHttpUtils
                .post()
                .url(getAbsoluteUrl(url))
                .addParams("lastPostId", Integer.toString(lastPostId))
                .build()
                .connTimeOut(20000)
                .execute(callback);
    }

    public static void checkForNew(String url, int accountId, Callback callback) {
        OkHttpUtils
                .post()
                .url(getAbsoluteUrl(url))
                .addParams("accountId", Integer.toString(accountId))
                .build()
                .connTimeOut(20000)
                .execute(callback);
    }

    public static void searchPost(String url, String title, int tag, Callback callback) {
        OkHttpUtils
                .post()
                .url(getAbsoluteUrl(url))
                .addParams("title", title)
                .addParams("tag", Integer.toString(tag))
                .build()
                .connTimeOut(20000)
                .execute(callback);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
