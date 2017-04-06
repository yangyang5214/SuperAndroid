package com.example.administrator.superandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.administrator.superandroid.R;

public class LibraryActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        webView = (WebView) findViewById(R.id.library);
        webView.loadUrl("http://www.niowoo.com/weixin.php/Home/Library/searchBook/library_id/696");
    }
}
