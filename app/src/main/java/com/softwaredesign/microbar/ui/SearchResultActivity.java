package com.softwaredesign.microbar.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.model.Record;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity implements OnClickListener {
    private ImageButton goBack;
    private String result;
    private SimpleAdapter adapter;
    private ListView showView;
    private List<Map<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        goBack = (ImageButton) findViewById(R.id.goBack);
        showView = (ListView) findViewById(R.id.showView);

        Intent intent = getIntent();
        result = intent.getStringExtra("result");

        parseJason();

        adapter = new SimpleAdapter(this, list, R.layout.search_result_items2,
                new String[]{"title", "comment", "time", "type"},
                new int[]{R.id.title, R.id.comment, R.id.time, R.id.type});
        showView.setAdapter(adapter);
        goBack.setOnClickListener(this);
    }

    private void parseJason() {
        list = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Record>>() {
        }.getType();
        List<Record> itemList = gson.fromJson(result, type);
        for (int i = 0; i < itemList.size(); i++) {
            Map<String, String> record = new HashMap<>();
            Record item = itemList.get(i);
            record.put("title", item.getTitle());
            record.put("comment", Integer.toString(item.getCommentNum()));
            record.put("time", item.getCreateTime());
            record.put("type", item.getTag());
            list.add(record);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
}