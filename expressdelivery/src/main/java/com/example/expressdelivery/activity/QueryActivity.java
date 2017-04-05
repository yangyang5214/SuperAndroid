package com.example.expressdelivery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.example.expressdelivery.R;
import com.example.expressdelivery.entity.CompanyEntity;
import com.example.expressdelivery.entity.Extras;
import com.example.expressdelivery.entity.RequestCode;
import com.example.expressdelivery.entity.SearchInfo;
import com.example.expressdelivery.entity.SuggestionResult;
import com.example.expressdelivery.util.HttpCallback;
import com.example.expressdelivery.util.HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryActivity extends AppCompatActivity {

    private EditText etPostId;
    private EditText etCompanyId;
    private Button mButtonSelect;
    private Button mButtonQuery;
    private SearchInfo searchInfo = new SearchInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        initView();
    }

    private void initView() {
        mButtonSelect = (Button) findViewById(R.id.select_company);
        mButtonQuery = (Button) findViewById(R.id.query_company);
        etPostId = (EditText) findViewById(R.id.et_post_id);
        etCompanyId = (EditText) findViewById(R.id.et_company_id);
        mButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
                startActivityForResult(intent,RequestCode.REQUEST_COMPANY);
            }
        });
        mButtonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInfo.setPost_id(etPostId.getText().toString());
                searchInfo.setCode(searchInfo.getCode());
                searchInfo.setName(searchInfo.getName());
                searchInfo.setLogo(searchInfo.getLogo());
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(Extras.SEARCH_INFO, searchInfo);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle bundle=data.getExtras();//data为B中回传的Intent
                searchInfo = (SearchInfo) bundle.getSerializable(Extras.SEARCH_INFO);
                etCompanyId.setText(searchInfo.getName());
                break;
            default:
                break;
        }
    }
}
