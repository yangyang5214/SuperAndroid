package com.example.expressdelivery.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.expressdelivery.R;
import com.example.expressdelivery.adapter.ResultAdapter;
import com.example.expressdelivery.entity.Extras;
import com.example.expressdelivery.entity.SearchInfo;
import com.example.expressdelivery.entity.SearchResult;
import com.example.expressdelivery.util.HttpCallback;
import com.example.expressdelivery.util.HttpClient;

public class ResultActivity extends AppCompatActivity{

    private SearchInfo mSearchInfo;
    private ImageView ivLogo;
    private LinearLayout llResult;
    private LinearLayout llNoExist;
    private LinearLayout llError;
    private TextView tvSearching;
    private ListView lvResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        Intent intent = getIntent();
        mSearchInfo = (SearchInfo) intent.getSerializableExtra(Extras.SEARCH_INFO);
        Glide.with(this)
                .load(HttpClient.urlForLogo(mSearchInfo.getLogo()))
                .dontAnimate()
                .placeholder(R.drawable.ic_default_logo)
                .into(ivLogo);
        query();
    }

    private void query() {
        HttpClient.query(mSearchInfo.getCode(), mSearchInfo.getPost_id(), new HttpCallback<SearchResult>() {
            @Override
            public void onResponse(SearchResult searchResult) {
                onSearch(searchResult);
            }

            @Override
            public void onError(VolleyError volleyError) {
                llResult.setVisibility(View.GONE);
                llNoExist.setVisibility(View.GONE);
                llError.setVisibility(View.VISIBLE);
                tvSearching.setVisibility(View.GONE);
            }
        });
    }

    private void onSearch(SearchResult searchResult) {
        if (searchResult.getStatus().equals("200")) {
            llResult.setVisibility(View.VISIBLE);
            llNoExist.setVisibility(View.GONE);
            llError.setVisibility(View.GONE);
            tvSearching.setVisibility(View.GONE);
            lvResultList.setAdapter(new ResultAdapter(searchResult));
            mSearchInfo.setIs_check(searchResult.getIscheck());
        } else {
            llResult.setVisibility(View.GONE);
            llNoExist.setVisibility(View.VISIBLE);
            llError.setVisibility(View.GONE);
            tvSearching.setVisibility(View.GONE);
        }
    }

    private void initView() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        llResult  = (LinearLayout) findViewById(R.id.ll_result);
        llNoExist  = (LinearLayout) findViewById(R.id.ll_no_exist);
        llError = (LinearLayout) findViewById(R.id.ll_error);
        tvSearching = (TextView) findViewById(R.id.tv_searching);
        lvResultList = (ListView) findViewById(R.id.lv_result_list);
    }
}
