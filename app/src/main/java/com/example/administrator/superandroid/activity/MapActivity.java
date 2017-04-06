package com.example.administrator.superandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.ConfigUtil;

public class MapActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        webView = (WebView) findViewById(R.id.map_webview);
        webView.loadUrl(ConfigUtil.getValueByKey(getApplicationContext(),"school.map.url"));
    }

}
