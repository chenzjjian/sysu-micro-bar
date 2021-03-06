package com.softwaredesign.microbar.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.util.PostUtil;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;


public class MainActivity extends AppCompatActivity {
    private static final String CHECKNEW = "checkMessage";

    private PostFragment mainPage;
    private PostFragment recentlyWatch;
    private PostFragment commentReply;
    private PostFragment myPost;

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
        String stuNo = sp.getString("stuNo", "");
        String nickname = sp.getString("nickname", "");
        String headImageUrl = sp.getString("headImageUrl","");
        String pwd = sp.getString("PASSWORD", "");
        userName.setText(nickname);
        if (!headImageUrl.isEmpty()) {
            Picasso.with(this)
                    .load(headImageUrl)
                    .placeholder(R.drawable.default_portrait)  //默认(加载前)头像
                    .error(R.drawable.default_portrait)  //加载失败时的头像
                    .resizeDimen(R.dimen.portrait_width, R.dimen.portrait_height)
                    .centerInside()
                    .into(userPortrait);
        }
        checkForNew();
    }

    public void init() {
        mainPageToolbar = (Toolbar) findViewById(R.id.mainPageToolbar);
        mainPageToolbar.inflateMenu(R.menu.mainpage_menu);
        mainPageToolbar.setNavigationIcon(R.drawable.ic_dehaze_white_24dp);
        setSupportActionBar(mainPageToolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 侧边栏
        navigation = (NavigationView) findViewById(R.id.navigation);
        navHeaderView = navigation.getHeaderView(0);
        userPortrait = (ImageView) navHeaderView.findViewById(R.id.userPortrait);
        userName = (TextView) navHeaderView.findViewById(R.id.userName);
        comment_layout = (LinearLayout) navigation.getMenu().findItem(R.id.menu_commentReply).getActionView();
        comment_layout.setVisibility(View.INVISIBLE);
        comment_message = (TextView) comment_layout.findViewById(R.id.message);
        oldItem = navigation.getMenu().getItem(0);
        oldItem.setChecked(true);
    }

    public void addListener() {
        mainPageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigation);
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_homePage:
                        if (mainPage == null) {
                            mainPage = PostFragment.getPostFragment(PostFragment.POSTTYPE.HOMEPAGE);
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, mainPage).commit();
                        break;
                    case R.id.menu_editAccount:
                        break;
                    case R.id.menu_recentlyWatch:
                        if (recentlyWatch == null) {
                            recentlyWatch = PostFragment.getPostFragment(PostFragment.POSTTYPE.HISTORY);
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, recentlyWatch).commit();
                        break;
                    case R.id.menu_commentReply:
                        break;
                    case R.id.menu_myPost:
                        if (myPost == null) {
                            myPost = PostFragment.getPostFragment(PostFragment.POSTTYPE.MYPOST);
                        }
                        myPost.setAccountId(accountId);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, myPost).commit();
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
        mainPage = PostFragment.getPostFragment(PostFragment.POSTTYPE.HOMEPAGE);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, mainPage).commit();
    }

    private void checkForNew() {
        PostUtil.checkForNew(CHECKNEW, accountId, new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response) {
                if (response.contains("true")) {
                    comment_layout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "有新消息", Toast.LENGTH_SHORT).show();
                } else if (response.contains("false")) {
                    Toast.makeText(MainActivity.this, "木有新消息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}