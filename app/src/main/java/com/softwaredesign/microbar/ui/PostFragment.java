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

import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.adapter.PostAdapter;
import com.softwaredesign.microbar.model.Post;
import com.softwaredesign.microbar.util.PostUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 16/6/21.
 */
// 与帖子有关的Fragment
public class PostFragment extends Fragment {

    public enum POSTTYPE {
        HOMEPAGE, HISTORY, MYPOST, SEARCH
    }

    private FragmentActivity fragmentActivity;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadMoreListView mLoadMoreListView;
    private PostAdapter postAdapter;
    private List<Post> posts = new ArrayList<>();
    private String title;
    private int tag;
    private POSTTYPE postType;

    public PostFragment() {
    }

    public static PostFragment getPostFragment(POSTTYPE key) {
        Bundle arguments = new Bundle();
        arguments.putSerializable("KEY", key);
        PostFragment postFragment = new PostFragment();
        postFragment.setArguments(arguments);
        return postFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        Bundle arguments = getArguments();
        // 决定获取哪种数据(首页/最近看过/我发的帖)
        postType = (POSTTYPE) arguments.getSerializable("KEY");
        init(view);
        setListener();
        return view;
    }

    public void init(View v) {
        fragmentActivity = getActivity();
        // 主页
        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.mainPage);
        mLoadMoreListView = (LoadMoreListView) v.findViewById(R.id.mainListView);
        postAdapter = new PostAdapter(fragmentActivity, posts);
        Log.d("PostFragment", "123");
        mLoadMoreListView.setAdapter(postAdapter);
        switch (postType) {
            case HOMEPAGE:
                PostUtil.lookForMore(posts, 0);
                postAdapter.notifyDataSetChanged();
                break;
            case HISTORY:
                break;
            case MYPOST:
                break;
            case SEARCH:
                PostUtil.searchPost(posts, title, tag);
                postAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
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
                Post post = posts.get(position);
                int postId = post.getPostId();

                Bundle data = new Bundle();
                data.putInt("postId", postId);

                Intent intent = new Intent(fragmentActivity, TestActivity2.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    public void setSearchTitle(String searchTitle) {
        title = searchTitle;
    }

    public void setSearchTag(int searchTag) {
        tag = searchTag;
    }
}
