package com.example.administrator.superandroid.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.AlbumGridViewAdapter;
import com.example.administrator.superandroid.dto.AlbumHelper;
import com.example.administrator.superandroid.dto.ImageBucket;
import com.example.administrator.superandroid.dto.ImageItem;
import com.example.administrator.superandroid.view.Bimp;

import java.util.ArrayList;
import java.util.List;


/**
 * 这个是进入相册显示所有图片的界面
 */
public class AlbumActivity extends AppCompatActivity {

    //显示手机里的所有图片的列表控件
    private GridView gridView;
    //gridView的adapter
    private AlbumGridViewAdapter gridImageAdapter;
    private Intent intent;
    private TextView okText;//完成按钮
    private TextView preview; // 预览按钮
    private Context mContext;
    private ArrayList<ImageItem> dataList;
    private AlbumHelper helper;
    public static List<ImageBucket> contentList;

    private Toolbar mToolBar;
    private TextView mTitleText;
    private TextView mTitleLeft;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_album);
        mContext = getBaseContext();
        //注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        initView();
        initData();
        initToolBar();
        isShowOkBt(); //这个函数主要用来控制预览和完成按钮的状态
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleLeft = (TextView) findViewById(R.id.title_left);
        mTitleLeft.setText("相册");
        mTitleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumActivity.this, ImageFile.class);
                startActivity(intent);
                finish();
            }
        });
        mTitleText.setText("所有照片");
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
    }

    //返回按钮设置监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recall:
                Bimp.tempSelectBitmap.clear();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //右上角的取消按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moving_photo_sum_recall, menu);
        return super.onCreateOptionsMenu(menu);
    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            gridImageAdapter.notifyDataSetChanged();
        }
    };

    // 初始化，给一些对象赋值
    private void initView() {
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<ImageItem>();
        for (int i = 0; i < contentList.size(); i++) {
            dataList.addAll(contentList.get(i).imageList);
        }
        //预览按钮的监听
        preview = (TextView) findViewById(R.id.preview);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        gridView = (GridView) findViewById(R.id.myGrid);
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        //完成按钮设置监听
        okText = (TextView) findViewById(R.id.ok_button);
    }

    private void initData() {
        //预览的监听事件
        preview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Bimp.tempSelectBitmap.size() > 0) {
                    intent.putExtra("position", "1");
                    intent.setClass(AlbumActivity.this, GalleryActivity.class);
                    startActivity(intent);
                }
            }
        });
        //gridview的监听事件，动态设置完成按钮的文本内容
        gridImageAdapter.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToggleButton view, int position, boolean isChecked, Button chooseBt) {
                if (Bimp.tempSelectBitmap.size() >= 9) {
                    view.setChecked(false);
                    chooseBt.setVisibility(View.GONE);
                    if (!removeOneData(dataList.get(position))) {
                        Toast.makeText(AlbumActivity.this, R.string.only_choose_num, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if (isChecked) {
                    chooseBt.setVisibility(View.VISIBLE);
                    Bimp.tempSelectBitmap.add(dataList.get(position));
                    okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
                } else {
                    Bimp.tempSelectBitmap.remove(dataList.get(position));
                    chooseBt.setVisibility(View.GONE);
                    okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
                }
                isShowOkBt();
            }
        });
        //完成按钮的点击事件
        okText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean removeOneData(ImageItem imageItem) {
        if (Bimp.tempSelectBitmap.contains(imageItem)) {
            Bimp.tempSelectBitmap.remove(imageItem);
            okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
            preview.setPressed(true);
            okText.setPressed(true);
            preview.setClickable(true);
            okText.setClickable(true);
            okText.setBackgroundResource(R.color.blue);
            preview.setTextColor(Color.parseColor("#1EB9F2"));
        } else {
            okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
            preview.setPressed(false);
            preview.setClickable(false);
            okText.setPressed(false);
            okText.setClickable(false);
            okText.setBackgroundResource(R.color.colorFleet);
            okText.setTextColor(Color.parseColor("#E9EBEC"));
            preview.setTextColor(Color.parseColor("#C4C8C4"));
        }
    }


    @Override
    protected void onRestart() {
        isShowOkBt();
        super.onRestart();
    }


    //解注册广播
    @Override
    protected void onDestroy() {
        mContext.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
