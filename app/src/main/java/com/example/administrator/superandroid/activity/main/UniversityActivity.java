package com.example.administrator.superandroid.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.ConfigUtil;

public class UniversityActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        initView();
        initToolBar();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(ConfigUtil.getValueByKey(getApplicationContext(),"school.Introduction"));
    }


    private void initToolBar() {
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleText.setText("详情");
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //系统的返回键，前面加android。R.id
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
