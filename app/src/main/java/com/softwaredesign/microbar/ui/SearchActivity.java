package com.softwaredesign.microbar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaredesign.microbar.R;

public class SearchActivity extends AppCompatActivity {
    private static final int SEARCH = 3;

    private Toolbar searchToolbar;
    private Spinner searchType;
    private EditText searchTitle;
    private String title;
    private Integer tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchToolbar = (Toolbar) findViewById(R.id.searchToolbar);
        searchToolbar.inflateMenu(R.menu.search_menu);
        searchToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(searchToolbar);
        TextView customTitle = (TextView) searchToolbar.findViewById(R.id.customTitle);
        if (customTitle != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            customTitle.setText("搜索帖子");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchTitle = (EditText) findViewById(R.id.search_title);
        searchType = (Spinner) findViewById(R.id.search_type);

        final String[] postTags = getResources().getStringArray(R.array.postTags);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, postTags);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchType.setAdapter(adapter);
        searchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tag = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.searchPost:
                doSearch();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doSearch() {
        title = searchTitle.getText().toString();
        if (title.isEmpty()) {
            Toast.makeText(SearchActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putInt("tag", tag);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}