package com.softwaredesign.microbar.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.softwaredesign.microbar.R;
import com.softwaredesign.microbar.adapter.FloorAdapter;
import com.softwaredesign.microbar.model.Floor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 16/6/3.
 */
public class FloorActivity extends AppCompatActivity {
    private TextView title;
    private ListView container;
    private FloorAdapter floorAdapter;
    private List<Floor> floors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);
        title = (TextView) findViewById(R.id.title);
        title.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = title.getMeasuredWidth();
        container = (ListView) findViewById(R.id.container);
        initData();
        floorAdapter = new FloorAdapter(this, floors, width);
        container.setAdapter(floorAdapter);
        container.setOnScrollListener(new FloorScrollListener(this));
    }

    public void initData() {
        floors = new ArrayList<>();
        String url1 = "http://ww2.sinaimg.cn/crop.0.0.1080.1080.1024/d773ebfajw8eum57eobkwj20u00u075w.jpg";
        String url2 = "http://g.hiphotos.baidu.com/image/h%3D300/sign=cef5463d53e736d147138a08ab524ffc/241f95cad1c8a786bfec42ef6009c93d71cf5008.jpg";
        String content1 = "如题,大家有什么想法?\n"+"<img src=\"http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728b2027e428cafcc3cec3fd2cb5.jpg\">";
        String content2 = "我觉得吧,偶尔当条单身狗也挺不错的\n"+"<img src=\"http://www.qqpk.cn/Article/UploadFiles/201411/20141116135722282.jpg\">"+"但是不能经常当,哈哈!";
        Floor floor1 = new Floor(url1,"黄勇进","10分钟前",content1);
        Floor floor2 = new Floor(url2,"黎峰君","刚刚",content2);
        floors.add(floor1);
        floors.add(floor2);
    }

    class FloorScrollListener implements AbsListView.OnScrollListener {
        private final Context context;

        public FloorScrollListener(Context context) {
            this.context = context;
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            final Picasso picasso = Picasso.with(context);
            if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                picasso.resumeTag(context);
            } else {
                picasso.pauseTag(context);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // Do nothing.
        }
    }
}
