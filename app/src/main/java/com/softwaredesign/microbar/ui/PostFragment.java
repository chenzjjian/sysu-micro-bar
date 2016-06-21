package com.softwaredesign.microbar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.adapter.PostAdapter;
import com.softwaredesign.microbar.model.mainObject;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 16/6/21.
 */
// 与帖子有关的Fragment
public class PostFragment extends Fragment {
    private final String getMoreURL = "http://119.29.178.68:8080/sysu-micro-bar/getPostList";

    private FragmentActivity fragmentActivity;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadMoreListView mLoadMoreListView;
    private PostAdapter mAdapter;
    private List<mainObject> mList = new ArrayList<>();
    private int postType;

    public PostFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        Bundle arguments = getArguments();
        // 决定获取哪种数据(首页/最近看过/我发的帖)
        postType = arguments.getInt("Key");
        init(view);
        setListener();
        return view;
    }

    public void init(View v) {
        fragmentActivity = getActivity();
        // 主页
        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.mainPage);
        mLoadMoreListView = (LoadMoreListView) v.findViewById(R.id.mainListView);
        mAdapter = new PostAdapter(fragmentActivity, mList);
        mLoadMoreListView.setAdapter(mAdapter);
        // 获取显示数据
        lookForMore(0);
        //checkForNew(accountId);
    }

    public void setListener() {
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("MainActivity", "refresh");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //refreshForNew(mList.get(0).getPostId());
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        //上拉加载
        mLoadMoreListView.setOnLoadListener(new LoadMoreListView.OnLoadListener() {
            @Override
            public void onLoad() {
                Log.d("MainActivity", "loading");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //lookForMore(mList.size());
                        mLoadMoreListView.setLoading(false);
                    }
                }, 2000);
            }
        });
        mLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainObject obj = mList.get(position);
                Log.i("444111111", ""+obj.getPostId());
                int postId = obj.getPostId();

                Bundle data = new Bundle();
                data.putInt("postId", postId);

                Intent intent = new Intent(fragmentActivity, TestActivity2.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    // 查看更多帖子
    public void lookForMore(int postTotal) {
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
                        Toast.makeText(fragmentActivity, "获取新帖子失败，请检查网络或重新获取",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<mainObject> mainobject = gson.fromJson(response, new TypeToken<List<mainObject>>() {}.getType());
                        mList.addAll(mainobject);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void refreshForNew(int lastPostId) {
        OkHttpUtils
                .post()
                .addParams("lastPostId", Integer.toString(lastPostId))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                        Toast.makeText(fragmentActivity, "刷新失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<mainObject> mainObjects = gson.fromJson(response, new TypeToken<List<mainObject>>() {}.getType());
                        mList.addAll(0, mainObjects);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
