package com.softwaredesign.microbar.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaredesign.microbar.model.Post;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * Created by mac on 16/6/23.
 */
public class PostUtil {

    private static final String getMoreURL = "http://119.29.178.68:8080/sysu-micro-bar/getPostList";
    private static final String searchPostUrl = "http://119.29.178.68:8080/sysu-micro-bar/searchPostList";

    // 查看更多帖子
    public static void lookForMore(final List<Post> posts, int postTotal) {
        OkHttpUtils
                .post()
                .url(getMoreURL)
                .addParams("currentPostNum", Integer.toString(postTotal))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<Post> newPosts = gson.fromJson(response, new TypeToken<List<Post>>() {}.getType());
                        posts.addAll(newPosts);
                    }
                });
    }

    public static void refreshForNew(final List<Post> posts, int lastPostId) {
        OkHttpUtils
                .post()
                .addParams("lastPostId", Integer.toString(lastPostId))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<Post> newPosts = gson.fromJson(response, new TypeToken<List<Post>>() {}.getType());
                        posts.addAll(0, newPosts);
                    }
                });
    }

    public static void searchPost(final List<Post> posts, String title, int tag) {
        OkHttpUtils
                .post()
                .url(searchPostUrl)
                .addParams("title", title)
                .addParams("tag", Integer.toString(tag))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("user", response);
                        if (response.equals("[]")) {
                            Log.d("PostUtil", "没有匹配的结果");
                        } else {
                            Gson gson = new Gson();
                            List<Post> newPosts = gson.fromJson(response, new TypeToken<List<Post>>() {}.getType());
                            posts.addAll(newPosts);
                        }
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.d("PostUtil", "搜索失败");
                        e.printStackTrace();
                    }
                });
    }
}
