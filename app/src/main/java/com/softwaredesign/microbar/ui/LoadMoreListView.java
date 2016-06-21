package com.softwaredesign.microbar.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.softwaredesign.microbar.R;

/**
 * Created by mac on 16/6/19.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    private int footerViewHeight;
    // 监听上拉加载
    private OnLoadListener mOnLoadListener;
    // 底部加载时View
    protected View mListViewFooter;
    private boolean isLoading = false;
    private boolean isBottom = false;

    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
        this.setOnScrollListener(this);
    }

    private void initFooterView(Context context) {
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
        mListViewFooter.measure(0, 0);
        footerViewHeight = mListViewFooter.getMeasuredHeight();
        mListViewFooter.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(mListViewFooter);
    }

    // 监听滚动状态变化
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isBottom && !isLoading) {
                loadData();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isBottom = (getLastVisiblePosition() == (totalItemCount-1));
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListViewFooter.setPadding(0,0,0,0);
            this.setSelection(this.getCount());
        } else {
            mListViewFooter.setPadding(0,-footerViewHeight,0,0);
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }
}
