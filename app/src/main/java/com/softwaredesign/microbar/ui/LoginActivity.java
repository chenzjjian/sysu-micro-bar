package com.softwaredesign.microbar.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaredesign.microbar.R;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView register;
    private EditText login_id;
    private EditText login_password;
    private String id;
    private String password;
    private String login_url = "http://119.29.178.68:8080/sysu-micro-bar/account/doLogin";

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        register = (TextView) findViewById(R.id.register);
        login_id = (EditText) findViewById(R.id.login_id);
        login_password = (EditText) findViewById(R.id.login_password);

        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        int accountId = sp.getInt("accountId", -1);
        String stuNo = sp.getString("stuNo", "");
        String pwd = sp.getString("PASSWORD", "");


        if (accountId == -1) {
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = login_id.getText().toString();
                password = login_password.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入学号", Toast.LENGTH_LONG).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                } else {
                    check_and_login(id, password);
                }
            }
        });
    }

    public void check_and_login(final String id, final String password) {
        JSONObject jason = new JSONObject();
        try {
            jason.put("password", password);
            jason.put("StuNo", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(login_url)
                .content(jason.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .connTimeOut(3000)
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String result) {
                        String message = "";
                        if (result.indexOf("用户还没注册") > 0) {
                            message = "用户还没注册";
                        } else if (result.indexOf("密码不匹配") > 0) {
                            message = "密码不匹配";
                        }
                        if (result.indexOf("true") > 0) {

                            String s2 = "";
                            for (int i = 0; i < result.length(); i++) {
                                //System.out.println(result.charAt(i));
                                if (result.charAt(i) >= '1' && result.charAt(i) <= '9') {
                                    s2 += result.charAt(i);
                                }
                            }

                            sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("accountId", Integer.parseInt(s2));
                            editor.putString("stuNo", id);
                            editor.putString("PASSWORD", password);
                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
//					    Gson gson = new Gson();
//					    Response response = gson.fromJson(result, Response.class);
//					    login_result = response.getLoginStatus();
//						Toast.makeText(MainActivity.this, gson.toJson(response), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                });
    }

}