package com.example.administrator.superandroid.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
import com.example.administrator.superandroid.dto.ImageItem;
import com.example.administrator.superandroid.view.Bimp;

import java.util.ArrayList;

/**
 * 这个是显示一个文件夹里面的所有图片时的界面
 */
public class ShowAllPhoto extends AppCompatActivity {

    private GridView gridView;
    private AlbumGridViewAdapter gridImageAdapter;
    // 完成按钮
    private TextView okText;
    // 预览按钮
    private TextView preview;
    private Intent intent;
    private Context mContext;

    private Toolbar mToolBar;
    private TextView mTitleText;
    private TextView mTitleLeft;
    private String folderName;
    public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_show_all_photo);
        mContext = getBaseContext();
        this.intent = getIntent();
        folderName = intent.getStringExtra("folderName");
        if (folderName.length() > 8) {
            folderName = folderName.substring(0, 9) + "...";
        }
        initView();
        initToolBar();
        initData();
        isShowOkBt();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            gridImageAdapter.notifyDataSetChanged();
        }
    };

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleLeft = (TextView) findViewById(R.id.title_left);
        mTitleLeft.setText("相册");
//        mTitleLeft.setTextColor(Color.parseColor("#1EB9F2"));
        mTitleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                intent.setClass(ShowAllPhoto.this, ImageFile.class);
                startActivity(intent);
            }
        });
        mTitleText.setText(folderName);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
    }


    //返回按钮设置监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recall:
                //清空选择的图片
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

    private void initView() {
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        gridView = (GridView) findViewById(R.id.showallphoto_myGrid);
        okText = (TextView) findViewById(R.id.showallphoto_ok_button);
        preview = (TextView) findViewById(R.id.showallphoto_preview);
    }

    private void initData() {
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToggleButton view, int position, boolean isChecked, Button chooseBt) {
                if (Bimp.tempSelectBitmap.size() >= 9 && isChecked) {
                    chooseBt.setVisibility(View.GONE);
                    view.setChecked(false);
                    Toast.makeText(ShowAllPhoto.this, R.string.only_choose_num, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (isChecked) {
                    chooseBt.setVisibility(View.VISIBLE);
                    Bimp.tempSelectBitmap.add(dataList.get(position));
                    okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
                } else {
                    chooseBt.setVisibility(View.GONE);
                    Bimp.tempSelectBitmap.remove(dataList.get(position));
                    okText.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + ")");
                }
                isShowOkBt();
            }
        });
        preview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Bimp.tempSelectBitmap.size() > 0) {
                    intent.putExtra("position", "2");
                    intent.setClass(ShowAllPhoto.this, GalleryActivity.class);
                    startActivity(intent);
                }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent.setClass(ShowAllPhoto.this, ImageFile.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

}
