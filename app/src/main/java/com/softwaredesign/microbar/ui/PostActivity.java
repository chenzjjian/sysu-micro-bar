package com.softwaredesign.microbar.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.loopj.android.http.RequestParams;
import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.util.ImageUtil;
import com.softwaredesign.microbar.util.UploadUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mac on 16/6/3.
 */
public class PostActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 0;
    private static final int TAKE_PHOTO = 1;
<<<<<<< HEAD
    private static final String UPLOAD_URL = "http://172.18.40.194:8080/sysu-micro-bar/createPost";
=======
    private static final String UPLOAD_URL = "http://119.29.178.68:8080/sysu-micro-bar/createPost";
>>>>>>> fe7ac7940d1539a134c02790186e7fc12b60224e

    private Toolbar postToolbar;
    private EditText postTitle;
    private Spinner postTagSpinner;
    private int postTag;
    private EditText content;
    // Key: uuid, Value: path
    private HashMap<String, String> pictures;

    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
        setListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.select_picture:
                getImageFromGallery();
                break;
            case R.id.take_photo:
                File file = createPhotoFile();
                Log.i("PostActivity", "photo's path is " + file);
                outputFileUri = Uri.fromFile(file);
                Log.i("PostActivity", "FileUri is " + outputFileUri);
                takePhoto(outputFileUri);
                break;
            case R.id.commit:
                uploadPost();
                Log.i("PostActivity", "" + content.getText());
                Intent intent = new Intent(PostActivity.this, FloorActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void init() {
        postToolbar = (Toolbar) findViewById(R.id.postToolbar);
        postToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(postToolbar);
        postTitle = (EditText)findViewById(R.id.postTitle);
        postTagSpinner = (Spinner) findViewById(R.id.postTag);
        content = (EditText) findViewById(R.id.content);
        pictures = new HashMap<>();
    }

    public void setListener() {
        final String[] postTags = getResources().getStringArray(R.array.postTags);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, postTags);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postTagSpinner.setAdapter(adapter);
        postTagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                postTag = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
<<<<<<< HEAD
        select_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = createPhotoFile();
                Log.i("ImageAndTest", "photo's path is " + file);
                outputFileUri = Uri.fromFile(file);
                Log.i("ImageAndText", "FileUri is " + outputFileUri);
                takePhoto(outputFileUri);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPost();
                Log.i("ImageAndText", "" + content.getText());
                Intent intent = new Intent(PostActivity.this, FloorActivity.class);
                startActivity(intent);
            }
        });
=======
>>>>>>> fe7ac7940d1539a134c02790186e7fc12b60224e
    }

    // 从系统相册中获取图片
    public void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
    }

    /**
     * 创建存储照片的文件
     *
     * @return 照片的存储路径(这里设置为公有的Pictures路径)
     */
    public static File createPhotoFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        try {
            return File.createTempFile(imageFileName, ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拍照获取图片并保存在系统的Pictures路径下
     *
     * @param uri 拍照前产生的用来保存图片的Uri
     */
    public void takePhoto(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 上传帖子
     */
    public void uploadPost() {
        RequestParams params = new RequestParams();
        UploadUtil.addTitleAndTag(params, 13331095, postTitle, postTag);
        UploadUtil.addContent(params, content, pictures);
        UploadUtil.sendMultipartRequest(UPLOAD_URL, params);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 返回不成功时的处理
        if (resultCode != RESULT_OK) {
            Log.d("ImageAndText", "canceled or other exception!");
            switch (requestCode) {
                case SELECT_PICTURE:
                    Log.i("ImageAndText", "don't pick any picture");
                    break;
                case TAKE_PHOTO:
                    deleteEmptyPhotoPath(outputFileUri);
                    break;
                default:
                    break;
            }
            return;
        }

        switch (requestCode) {
            case SELECT_PICTURE:
                Uri contentUri = data.getData();
                Log.i("ImageAndText", "Uri: " + contentUri);
                insertImageIntoText(content, contentUri, pictures);
                break;

            case TAKE_PHOTO:
                insertImageIntoText(content, outputFileUri, pictures);
                // 发送Media Scanner更新通知
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outputFileUri));
                break;
            default:
                break;
        }
    }

    /**
     * 删掉拍照时产生的空文件(创建了文件却没有拍照导致文件为空)
     *
     * @param uri 拍照前创建的Uri
     * @return 是否删除成功
     */
    public boolean deleteEmptyPhotoPath(Uri uri) {
        String photoPath = getRealPathFromURI(uri);
        File f = new File(photoPath);
        Log.i("ImageAndText", "photo path is " + photoPath);
        if (f.exists()) {
            return f.delete();
        }
        return false;
    }

    /**
     * 将图片插入到文本中
     *
     * @param editText           文本对应的EditTex对象
     * @param contentUri         插入图片的Uri
     * @param spanStrings_pathes 存储图片的spanString和path
     */
    public void insertImageIntoText(EditText editText, Uri contentUri, Map<String, String> spanStrings_pathes) {
        // 获取真正的图片路径
        // Android4.4之后返回的URI只有图片编号,而不是真正的路径
        String path = getRealPathFromURI(contentUri);

        // 直接获取图片,对内存消耗太大,很容易OOM
        // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentUri);

        // 压缩图片
        // 返回图片的宽度为EditText的宽度减去16或以上,
        // 否则在EditText显示一张图片时会出现两张相同的图片
        Bitmap bitmap = ImageUtil.decodeSampledBitmapFromFile(path, editText.getWidth() - 16, editText.getWidth());
        Log.i("ImageAndText", "" + bitmap.getWidth());
        Log.i("ImageAndText", "" + bitmap.getHeight());

        // 利用ImageSpan和SpannableString来显示图片
        String id = "[img=" + UUID.randomUUID() + "]";
        SpannableString spanString = new SpannableString(id);
        ImageSpan imageSpan = new ImageSpan(this, bitmap);
        spanString.setSpan(imageSpan, 0, id.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        int index = editText.getSelectionStart();
        Editable editable = editText.getEditableText();

        if (index < 0 || index >= editable.length()) {
            editable.append(spanString);
        } else {
            editable.insert(index, spanString);
        }

        // 将图片的标识和路径存储为Map,用于上传到服务器
        spanStrings_pathes.put(id, path);
        Log.i("ImageAndText", id);
    }

    /**
     * 获取Uri对应的真实路径
     *
     * @param contentUri 需要获取真正路径的Uri
     * @return
     */
    public String getRealPathFromURI(Uri contentUri) {
        String path;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            Log.i("ImageAndText", "cursor is null");
            path = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA);
            path = cursor.getString(idx);
            cursor.close();
        }
        Log.i("ImageAndText", "path: " + path);
        return path;
    }
}
