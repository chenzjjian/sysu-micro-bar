package com.softwaredesign.microbar.util;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mac on 16/6/4.
 */
public class UploadUtil {

    public static RequestParams addTitleAndTag(RequestParams params, int accountId, EditText postTitle, int postTag) {
        params.put("accountId", accountId);
        params.put("title", postTitle.getText());
        params.put("tag", postTag);
        return params;
    }

    public static RequestParams addContent(RequestParams params, EditText content, Map<String, String> spanStrings_pathes) {
        Editable editable = content.getText();
        params.put("detail", editable);

        // 利用正则表达式在文本中去匹配表示图片的key,得到图片的真实路径
        // 注意map中的键值对数目与实际的detail中的img数目可能不同(因为用户插入图片后又删除了该图片)
        // 所以需要重新计算实际需要上传的图片数目
        List<String> pathes = new ArrayList<>();
        String regex = "\\[img=\\w+-\\w+-\\w+-\\w+-\\w+\\]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(editable);
        while(m.find()) {
            pathes.add(spanStrings_pathes.get(m.group()));
            Log.d("UploadUtil", ""+pathes.size());
        }

        if (!pathes.isEmpty()) {
            File[] files = new File[pathes.size()];
            int count = 0;
            for (String path: pathes) {
                files[count++] = new File(path);
            }
            try {
                params.put("file", files);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    public static void sendRequest(String url, RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        params.setForceMultipartEntityContentType(true);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    if (headers != null) {
                        for (Header header : headers)
                            Log.i("Headers", header.getName() + ":" + header.getValue());
                    }
                    String response = new String(responseBody);
                    Log.i("ResponseBody", response);
                } else {
                    Log.i("onSuccess", "上传失败[" + statusCode + "错误]");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Test", new String(responseBody));
                Log.i("onFailure", "上传失败[" + statusCode + "错误]");
            }
        });
    }
}
