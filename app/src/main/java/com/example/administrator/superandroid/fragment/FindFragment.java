package com.example.administrator.superandroid.fragment;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.superandroid.MyApplication;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.PublishMovingActivity;
import com.example.administrator.superandroid.base.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FindFragment extends BaseFragment {

    private ImageView imageView;
    public void initData() {
        imageView = (ImageView) view.findViewById(R.id.publish_moving);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(),PublishMovingActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_moving, null);
        initData();
        return view;
    }


}
