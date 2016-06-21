package com.softwaredesign.microbar.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaredesign.microbar.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


public class MainActivity extends AppCompatActivity {

    private static final int HOMEPAGE = 0;
    private static final int HISTORY = 1;
    private static final int MYPOST = 2;

    private final String checkNewURL = "http://119.29.178.68:8080/sysu-micro-bar/checkMessage";

    private SharedPreferences sp;
    private int accountId;

    private Toolbar mainPageToolbar;

    // 侧边栏
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private View navHeaderView;
    private ImageView userPortrait;
    private TextView userName;

    private MenuItem oldItem;
    private LinearLayout comment_layout;
    private TextView comment_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            setDefaultFragment();
        }
        init();
        addListener();

        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        accountId = sp.getInt("accountId", -1);
        String stuNo = sp.getString("stuNo", "匿名");
        String pwd = sp.getString("PASSWORD", "");
        userName.setText(stuNo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainpage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainpage_search:
                break;
            case R.id.mainpage_add:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void init() {
        mainPageToolbar = (Toolbar) findViewById(R.id.mainPageToolbar);
        mainPageToolbar.inflateMenu(R.menu.mainpage_menu);
        setSupportActionBar(mainPageToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainPageToolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        // 侧边栏
        navigation = (NavigationView) findViewById(R.id.navigation);
        navHeaderView = navigation.getHeaderView(0);
        userPortrait = (ImageView) navHeaderView.findViewById(R.id.userPortrait);
        userName = (TextView) navHeaderView.findViewById(R.id.userName);
        comment_layout = (LinearLayout) navigation.getMenu().findItem(R.id.menu_commentReply).getActionView();
        comment_message = (TextView) comment_layout.findViewById(R.id.message);
        oldItem = navigation.getMenu().getItem(0);
        oldItem.setChecked(true);
    }

    public void addListener() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_homePage:
                        break;
                    case R.id.menu_editAccount:
                        break;
                    case R.id.menu_recentlyWatch:
                        break;
                    case R.id.menu_commentReply:
                        break;
                    case R.id.menu_myPost:
                        break;
                    case R.id.menu_exit:
                        break;
                    default:
                        break;
                }
                oldItem.setChecked(false);
                item.setChecked(true);
                oldItem = item;
                drawerLayout.closeDrawer(navigation);
                return true;
            }
        });
    }

    public void setDefaultFragment() {
        PostFragment postFragment = new PostFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Key", HOMEPAGE);
        postFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, postFragment).commit();
    }

    public void checkForNew(int accountId) {
        OkHttpUtils
                .post()
                .url(checkNewURL)
                .addParams("accountId", Integer.toString(accountId))
                .build()
                .connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "检查新消息失败",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i("5555", response);
                        if (response.contains("true")) {
                            Toast.makeText(MainActivity.this, "有新消息", Toast.LENGTH_SHORT).show();
                        } else if (response.contains("false")) {
                            Toast.makeText(MainActivity.this, "木有新消息", Toast.LENGTH_SHORT).show();
                            comment_layout.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}