package com.softwaredesign.microbar.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.readystatesoftware.viewbadger.BadgeView;
import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.model.mainObject;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements AbsListView.OnScrollListener {
    private Button exit, mainpageNewComment;
    private TextView testId, testPwd, testNo;
    private TextView leftMenuName;
    private SharedPreferences sp;

    private SlidingMenu slidingMenu;

    private ListView listView;

    private final String URL =
            "http://119.29.178.68:8080/sysu-micro-bar/getPostList";

    private final String newMsgURL =
            "http://119.29.178.68:8080/sysu-micro-bar/checkMessage";


    private static List<Map<String, Object>> myList = new ArrayList<>();
    private SimpleAdapter mAdapter;

    private View moreView, moreView2;
    private int firstItem, lastItem;

    private BadgeView badgeView, badgeView2;
    private ImageView mainPageMenu, mainPageSearch;
    private TextView mainPageMenuLeft;


    public void callForNew(int num, final int up) {
        OkHttpUtils
                .post()
                .url(URL)
                .addParams("currentPostNum", Integer.toString(num))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        //Log.e("2222222222222", "111");
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "获取新帖子失败，请检查网络或重新获取",
                                Toast.LENGTH_LONG).show();
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        List<mainObject> mainobject =
                                gson.fromJson(s, new TypeToken<List<mainObject>>() {
                                }.getType());
                        if (up == 1 || myList.isEmpty()) {
                            for (mainObject mO : mainobject) {
                                Log.i("333311111", mO.toString());
                                Map<String, Object> map = new HashMap<>();
                                map.put("title", mO.getTitle());
                                map.put("commentNum", mO.getCommentNum());
                                map.put("commentTime", mO.getCreateTime());
                                map.put("commentStyle", mO.getTag());
                                map.put("postId", Integer.toString(mO.getPostId()));

                                myList.add(map);
                                //mainObjList.add(i, mO);
                            }
                        } else {
                            int i = 0;
                            for (mainObject mO : mainobject) {
                                //Log.i("444411111", Integer.toString(myList.size()));
                                Log.i("333311111", mO.toString());
                                //if (!myList.isEmpty()) {
                                Log.i("44431postid", Integer.toString(mO.getPostId()));
                                Log.i("44431mylistid", ((String) myList.get(i).get("postId")));

                                if (Integer.toString(mO.getPostId()).equals(myList.get(i).get("postId"))) {
                                    break;
                                }

                                //}
                                Map<String, Object> map = new HashMap<>();
                                map.put("title", mO.getTitle());
                                map.put("commentNum", mO.getCommentNum());
                                map.put("commentTime", mO.getCreateTime());
                                map.put("commentStyle", mO.getTag());
                                map.put("postId", Integer.toString(mO.getPostId()));

                                myList.add(i, map);
                                i++;
                            }
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                });
    }


    public void checkNewMsg(int num) {
        OkHttpUtils
                .post()
                .url(newMsgURL)
                .addParams("accountId", Integer.toString(num))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        //Log.e("2222222222222", "111");
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "检查新消息失败",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("5555", s);
                        if (s.contains("true")) {
                            mHandler.sendEmptyMessage(1);
                        } else if (s.contains("false")) {
                            Log.i("5555", "testfalse");
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                });
    }

    public void toggleMenu(View view) {
        slidingMenu.toggle();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit = (Button) findViewById(R.id.exit);
        //testId = (TextView) findViewById(R.id.testid);
        //testPwd = (TextView) findViewById(R.id.testpwd);
        //testNo = (TextView) findViewById(R.id.testNo);
        leftMenuName = (TextView) findViewById(R.id.leftmenu_name);

        mainPageSearch = (ImageView) findViewById(R.id.mainpage_search);

        slidingMenu = (SlidingMenu) findViewById(R.id.menu);

        listView = (ListView) findViewById(R.id.mainListView);
        moreView = getLayoutInflater().inflate(R.layout.load, null);
        moreView2 = getLayoutInflater().inflate(R.layout.load2, null);

        mainPageMenu = (ImageView) findViewById(R.id.mainpage_menu);
        mainPageMenuLeft = (TextView) findViewById(R.id.mainpage_menu_left);
        mainpageNewComment = (Button) findViewById(R.id.mainpage_new_comment);
        badgeView = new BadgeView(this, mainPageMenuLeft);
        badgeView.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
        badgeView2 = new BadgeView(this, mainpageNewComment);
        badgeView2.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);


        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        int accountId = sp.getInt("accountId", -1);
        String stuNo = sp.getString("stuNo", "");
        String pwd = sp.getString("PASSWORD", "");

        leftMenuName.setText(stuNo);


        mAdapter = new SimpleAdapter(this, //没什么解释
                myList,//数据来源
                R.layout.main_list,//ListItem的XML实现

                //动态数组与ListItem对应的子项
                new String[]{"title", "commentNum", "commentTime", "commentStyle", "postId"},

                //ListItem的XML文件里面的两个TextView ID
                new int[]{R.id.commentTitle, R.id.commentNum, R.id.commentTime, R.id.commentStyle, R.id.postId});

        listView.addFooterView(moreView);
        listView.addHeaderView(moreView2);
        listView.setAdapter(mAdapter);
        callForNew(0, 0);
        listView.setOnScrollListener(this);


        //检查是否有新消息
        checkNewMsg(accountId);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String, Object> mapObj = myList.get(position - 1);
                Log.i("444111111", (String) mapObj.get("postId"));
                String s = (String) mapObj.get("postId");

                Bundle data = new Bundle();
                data.putString("postId", s);

                Intent intent = new Intent(MainActivity.this, TestActivity2.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });

        mainPageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        lastItem = firstVisibleItem + visibleItemCount - 2;  //减1是因为上面加了个addFooterView
        firstItem = firstVisibleItem;

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //Log.i(TAG, "scrollState="+scrollState);
        //下拉到空闲是，且最后一个item的数等于数据的总数时，进行更新
        if (lastItem == myList.size() && scrollState == SCROLL_STATE_IDLE) {
            //Log.i(TAG, "拉到最底部");
            moreView.setVisibility(View.VISIBLE);

            //mHandler.sendEmptyMessage(0);
            callForNew(myList.size(), 1);

        } else if (firstItem == 0 && scrollState == SCROLL_STATE_IDLE) {

            moreView2 = getLayoutInflater().inflate(R.layout.load, null);
            moreView2.setVisibility(View.VISIBLE);

            //mHandler.sendEmptyMessage(0);
            callForNew(0, 0);
        }

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    //callForNew(myList.size(), 1);
                    mAdapter.notifyDataSetChanged();
                    moreView.setVisibility(View.GONE);
                    moreView2 = getLayoutInflater().inflate(R.layout.load2, null);
                    moreView2.setVisibility(View.GONE);
                    break;
                case 1:
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                    badgeView.setText("N");
                    badgeView.show();
                    badgeView2.setText("N");
                    badgeView2.show();
                    break;
                default:
                    break;
            }
        }

    };


}