package com.example.administrator.superandroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ViewPageAdapter;
import com.example.administrator.superandroid.adapter.ViewPagerFixed;
import com.example.administrator.superandroid.view.Bimp;
import com.example.administrator.superandroid.view.PhotoView;

import java.util.ArrayList;


/**
 * 这个是用于进行图片浏览时的界面
 */
public class GalleryActivity extends AppCompatActivity {
    private Intent intent;

    private Toolbar mToolBar;
    private TextView mTitleText;

    //获取前一个activity传过来的position
    private int position;
    //当前的位置
    private int location = 0;

    private ArrayList<View> listViews;
    private ViewPagerFixed pager;
    private ViewPageAdapter adapter;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);
        mContext = getBaseContext();
        intent = getIntent();
        position = Integer.parseInt(intent.getStringExtra("position"));
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            initListViews(Bimp.tempSelectBitmap.get(i).getBitmap());
        }
        initToolBar();
        initView();
        initData();
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleText.setText(1 + "/" + Bimp.tempSelectBitmap.size());
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //返回按钮设置监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //系统的返回键，前面加android。R.id
            case android.R.id.home:
                finish();
                break;
            case R.id.delete:
                showNormalDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //删除对话框
    private void showNormalDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(GalleryActivity.this);
        dialog.setTitle("移除提示");
        dialog.setMessage("放弃上传这张照片？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listViews.size() == 1) {
                    Bimp.tempSelectBitmap.clear();
                    Bimp.max = 0;
                    mTitleText.setText(location + 1 + "/" + Bimp.tempSelectBitmap.size());
                    Intent intent = new Intent("data.broadcast.action");
                    sendBroadcast(intent);
                    finish();
                } else {
                    Bimp.tempSelectBitmap.remove(location);
                    Bimp.max--;
                    pager.removeAllViews();
                    listViews.remove(location);
                    adapter.setListViews(listViews);
                    mTitleText.setText(location + 1 + "/" + Bimp.tempSelectBitmap.size());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }


    private void initData() {
        adapter = new ViewPageAdapter(listViews,getApplicationContext());
        pager.setAdapter(adapter);
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }


    public void initView() {
        pager = (ViewPagerFixed) findViewById(R.id.gallery01);
        //给viewpager添加点击事件
        pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                location = position;
                mTitleText.setText(location + 1 + "/" + Bimp.tempSelectBitmap.size());
            }
            //此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //右上角的删除按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moving_share_photo_top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void initListViews(Bitmap bm) {
        if (listViews == null)
            listViews = new ArrayList<View>();
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(bm);
        img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        listViews.add(img);
    }
}
