package com.softwaredesign.microbar.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.softwaredesign.microbar.R;

public class SearchResultActivity extends AppCompatActivity {

    private Toolbar searchResultToolbar;
    private PostFragment searchResultFragment;
    private String title;
    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        tag = bundle.getInt("tag");
        setSearchResultFragment();
        init();
    }

    public void init() {
        searchResultToolbar = (Toolbar) findViewById(R.id.searchToolbar);
        searchResultToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(searchResultToolbar);
        TextView customTitle = (TextView) searchResultToolbar.findViewById(R.id.customTitle);
        if (customTitle != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            customTitle.setText("搜索结果");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setSearchResultFragment() {
        searchResultFragment = PostFragment.getPostFragment(PostFragment.POSTTYPE.SEARCH);
        searchResultFragment.setSearchTitle(title);
        searchResultFragment.setSearchTag(tag);
        getSupportFragmentManager().beginTransaction().replace(R.id.search_result, searchResultFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}