package com.example.administrator.superandroid.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ViewPageAdapter;
import com.example.administrator.superandroid.adapter.ViewPagerFixed;
import com.example.administrator.superandroid.util.ImageUtil;
import com.example.administrator.superandroid.view.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class ShowImageDetailsActivity extends AppCompatActivity {

    private ViewPagerFixed pager;
    private ViewPageAdapter adapter;
    private ArrayList<View> listViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);
        initView();
        initData();
    }

    private void initView() {
        pager = (ViewPagerFixed) findViewById(R.id.gallery01);

    }

    private void initData() {
        Intent intent = getIntent();
        listViews = new ArrayList<>();
        Bundle bundle = intent.getExtras();
        List<String> list =  bundle.getStringArrayList("imageList");
        for (String s : list) {
            listViews.add(initListViews(ImageUtil.getBitmap(s)));
        }
        adapter = new ViewPageAdapter(listViews,getApplicationContext());
        pager.setAdapter(adapter);
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    private View initListViews(Bitmap bm) {
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(bm);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return img;
    }
}
