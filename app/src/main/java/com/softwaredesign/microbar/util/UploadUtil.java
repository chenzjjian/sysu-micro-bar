package com.softwaredesign.microbar.util;

import android.text.Editable;
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

/**
 * Created by mac on 16/6/4.
 */
public class UploadUtil {
    private static final String BASE_URL = "http://119.29.178.68:8080/sysu-micro-bar/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.addHeader("Accept", "application/json");
    }
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

        // 利用正则表达式在文本中去匹配表示图片的key,得到图片的真实路径
        // 注意map中的键值对数目与实际的detail中的img数目可能不同(因为用户插入图片后又删除了该图片)
        // 所以需要重新计算实际需要上传的图片数目
        List<String> pathes = new ArrayList<>();
        String regex = "\\[img=\\w+-\\w+-\\w+-\\w+-\\w+\\]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(editable);
        while(m.find()) {
            pathes.add(spanStrings_pathes.get(m.group()));
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


    public static void sendMultipartRequest(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        params.setForceMultipartEntityContentType(true);
        client.post(getAbsoluteUrl(url), params, asyncHttpResponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}