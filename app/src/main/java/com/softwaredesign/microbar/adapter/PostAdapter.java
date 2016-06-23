package com.softwaredesign.microbar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.model.Post;

import java.util.List;

/**
 * Created by mac on 16/6/21.
 */
public class PostAdapter extends BaseAdapter {

    private Context mContext;
    private List<Post> posts;
    private LayoutInflater mInflater;

    public PostAdapter() {
    }

    public PostAdapter(Context mContext, List<Post> posts) {
        this.mContext = mContext;
        this.posts = posts;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.commentTitle = (TextView) convertView.findViewById(R.id.commentTitle);
            viewHolder.commentNum = (TextView) convertView.findViewById(R.id.commentNum);
            viewHolder.commentTime = (TextView) convertView.findViewById(R.id.commentTime);
            viewHolder.commentStyle = (TextView) convertView.findViewById(R.id.commentStyle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Post post = (Post) getItem(position);
        viewHolder.commentTitle.setText(post.getTitle());
        viewHolder.commentNum.setText(Integer.toString(post.getCommentNum()));
        viewHolder.commentTime.setText(post.getCreateTime());
        viewHolder.commentStyle.setText(post.getTag());
        return convertView;
    }

    static class ViewHolder {
        private TextView commentTitle;
        private TextView commentNum;
        private TextView commentTime;
        private TextView commentStyle;
    }
}
