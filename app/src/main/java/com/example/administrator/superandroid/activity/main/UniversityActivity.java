package com.example.administrator.superandroid.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.ConfigUtil;

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
        webView.loadUrl(ConfigUtil.getValueByKey(getApplicationContext(),"school.Introduction"));
        WebSettings settings = webView.getSettings();
        //适应屏幕
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
      settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
      settings.setLoadWithOverviewMode(true);
      settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
    }
}
