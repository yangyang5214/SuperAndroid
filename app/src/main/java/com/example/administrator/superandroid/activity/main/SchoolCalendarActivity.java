package com.example.administrator.superandroid.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.ConfigUtil;
import com.example.expressdelivery.util.HttpClient;

public class SchoolCalendarActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_calendar);
        imageView = (ImageView) findViewById(R.id.image);
        initView();
    }

    private void initView() {
        Glide.with(this)
                .load(ConfigUtil.getValueByKey(getApplicationContext(),"school.calendar.image.url"))
                .dontAnimate()
                .into(imageView);
    }

}
