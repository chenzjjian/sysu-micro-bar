package com.softwaredesign.microbar.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.softwaredesign.microbar.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


public class SearchActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {
    private Spinner type;
    private EditText title;
    private ArrayAdapter<String> type_adapter;
    private String[] typeList = {"学习", "运动", "交通", "美食", "娱乐", "其他"};
    private Integer search_type = 0;
    private String search_title;
    private ImageButton goBack, goSearch;
    private String search_url = "http://119.29.178.68:8080/sysu-micro-bar/searchPostList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        title = (EditText) findViewById(R.id.search_title);
        type = (Spinner) findViewById(R.id.search_type);
        goBack = (ImageButton) findViewById(R.id.goBack);
        goSearch = (ImageButton) findViewById(R.id.goSearch);

        goBack.setOnClickListener(this);
        goSearch.setOnClickListener(this);

        type_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeList);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(type_adapter);
        type.setOnItemSelectedListener(this);
    }

    private void doSearch() {
        search_title = title.getText().toString();
        OkHttpUtils.post().url(search_url).
                addParams("title", search_title)
                .addParams("tag", Integer.toString(search_type)).build()
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String result) {
                        Log.i("user", result);
//				Toast.makeText(Search_Activity.this, "搜索成功", Toast.LENGTH_SHORT).show();
                        if (result.equals("[]")) {
                            Toast.makeText(SearchActivity.this, "没有匹配的结果", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                            intent.putExtra("result", result);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(SearchActivity.this, "搜索失败", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack:
                finish();
                break;
            case R.id.goSearch:
                doSearch();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        search_type = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}