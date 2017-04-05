package com.example.expressdelivery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.expressdelivery.R;
import com.example.expressdelivery.adapter.SuggestionAdapter;
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

public class QueryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {

    private EditText etPostId;
    private ListView lvSuggestion;
    private Map<String, CompanyEntity> mCompanyMap = new HashMap<>();
    private List<CompanyEntity> mSuggestionList = new ArrayList<>();
    private SuggestionAdapter mSuggestionAdapter = new SuggestionAdapter(mSuggestionList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        readCompany();
        initView();
        lvSuggestion.setAdapter(mSuggestionAdapter);
    }

    private void initView() {
        etPostId = (EditText) findViewById(R.id.et_post_id);
        lvSuggestion = (ListView) findViewById(R.id.lv_suggestion);
        etPostId = (EditText) findViewById(R.id.et_post_id);
        lvSuggestion.setOnItemClickListener(this);
        etPostId.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(final Editable s) {
        mSuggestionList.clear();
        mSuggestionAdapter.notifyDataSetChanged();
        if (s.length() >= 8) {
            getSuggestion(s.toString());
        }
    }

    private void getSuggestion(final String postId) {
        HttpClient.getSuggestion(postId, new HttpCallback<SuggestionResult>() {
            @Override
            public void onResponse(SuggestionResult suggestionResult) {
                if (!TextUtils.equals(etPostId.getText().toString(), postId)) {
                    return;
                }
                onSuggestion(suggestionResult);
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (!TextUtils.equals(etPostId.getText().toString(), postId)) {
                    return;
                }
                onSuggestion(null);
            }
        });
    }

    private void onSuggestion(SuggestionResult response) {
        mSuggestionList.clear();
        if (response != null && response.getAuto() != null && !response.getAuto().isEmpty()) {
            for (SuggestionResult.AutoBean bean : response.getAuto()) {
                if (mCompanyMap.containsKey(bean.getComCode())) {
                    mSuggestionList.add(mCompanyMap.get(bean.getComCode()));
                }
            }
        }
        String label = "<font color='%1$s'>没有查到？</font> <font color='%2$s'>请选择快递公司</font>";
        String grey = String.format("#%06X", 0xFFFFFF & getResources().getColor(R.color.grey));
        String blue = String.format("#%06X", 0xFFFFFF & getResources().getColor(R.color.blue));
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(String.format(label, grey, blue));
        mSuggestionList.add(companyEntity);
        mSuggestionAdapter.notifyDataSetChanged();
    }

    private void readCompany() {
        try {
            InputStream is = getAssets().open("company.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(json).getAsJsonArray();
            for (JsonElement obj : jArray) {
                CompanyEntity company = gson.fromJson(obj, CompanyEntity.class);
                if (!TextUtils.isEmpty(company.getCode())) {
                    mCompanyMap.put(company.getCode(), company);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == mSuggestionList.size() - 1) {
            startActivityForResult(new Intent(this, CompanyActivity.class), RequestCode.REQUEST_COMPANY);
            return;
        }
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setPost_id(etPostId.getText().toString());
        searchInfo.setCode(mSuggestionList.get(position).getCode());
        searchInfo.setName(mSuggestionList.get(position).getName());
        searchInfo.setLogo(mSuggestionList.get(position).getLogo());
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra(Extras.SEARCH_INFO, searchInfo);
        startActivity(intent);
    }
}
