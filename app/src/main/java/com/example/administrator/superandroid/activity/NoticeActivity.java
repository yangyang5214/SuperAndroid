package com.example.administrator.superandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.ConfigUtil;

public class NoticeActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        webView = (WebView) findViewById(R.id.library);

        webView.loadUrl(ConfigUtil.getValueByKey(getApplicationContext(),"news.url"));

    }
}
