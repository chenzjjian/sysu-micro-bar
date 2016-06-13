package com.softwaredesign.microbar.util;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mac on 16/6/4.
 */
public class UploadUtil {
    /**
     *
     * @param params 上传参数
     * @param accountId 账户ID
     * @param postTitle 帖子标题
     * @param postTag 帖子标签
     * @return 上传参数
     */
    public static RequestParams addTitleAndTag(RequestParams params, int accountId, EditText postTitle, int postTag) {
        params.put("accountId", accountId);
        params.put("title", postTitle.getText());
        params.put("tag", postTag);
        return params;
    }

    /**
     *
     * @param params 上传参数
     * @param content 帖子内容
     * @param spanStrings_pathes
     * @return 上传参数
     */
    public static RequestParams addContent(RequestParams params, EditText content, Map<String, String> spanStrings_pathes) {
        Editable editable = content.getText();
        params.put("detail", editable);

        // 利用正则表达式在文本中去匹配表示图片的key
        File[] files = new File[spanStrings_pathes.size()];
        String regex = "\\[img=\\w+-\\w+-\\w+-\\w+-\\w+\\]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(editable);
<<<<<<< HEAD
        int count = 0;
        while (m.find()) {
            String path = spanStrings_pathes.get(m.group());
            files[count++] = new File(path);
=======
        while(m.find()) {
            pathes.add(spanStrings_pathes.get(m.group()));
>>>>>>> fe7ac7940d1539a134c02790186e7fc12b60224e
        }
        try {
            params.put("file", files);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return params;
    }


    public static void sendMultipartRequest(String url, RequestParams params) {
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
                Log.i("onFailure", new String(responseBody));
                Log.i("onFailure", "上传失败[" + statusCode + "错误]");
            }
        });
    }
}
