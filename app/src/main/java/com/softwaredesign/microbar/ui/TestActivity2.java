package com.softwaredesign.microbar.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softwaredesign.microbar.R;

/**
 * Created by lenovo on 2016/5/20.
 */
public class TestActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout2);


        TextView eT = (TextView) findViewById(R.id.test2_et);
        Intent intent = getIntent();
        int postId = intent.getIntExtra("postId", 0);

        eT.setText(Integer.toString(postId));

        Button button = (Button) findViewById(R.id.return_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}