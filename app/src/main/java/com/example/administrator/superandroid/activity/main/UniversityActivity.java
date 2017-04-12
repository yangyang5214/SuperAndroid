package com.example.administrator.superandroid.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.administrator.superandroid.R;

public class UniversityActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view);
    }
}
