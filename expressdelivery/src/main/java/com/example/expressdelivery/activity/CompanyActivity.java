package com.example.expressdelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.expressdelivery.R;
import com.example.expressdelivery.adapter.CompanyAdapter;
import com.example.expressdelivery.entity.CompanyEntity;
import com.example.expressdelivery.entity.Extras;
import com.example.expressdelivery.entity.SearchInfo;
import com.example.expressdelivery.view.IndexBar;
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

public class CompanyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, IndexBar.OnIndexChangedListener{


    private ListView lvCompany;
    private IndexBar ibIndicator;
    private TextView tvIndicator;
    private Map<String, CompanyEntity> mCompanyMap = new HashMap<>();
    private List<CompanyEntity> mCompanyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initView();
        readCompany();
        lvCompany.setAdapter(new CompanyAdapter(mCompanyList));
        lvCompany.setOnItemClickListener(this);
        ibIndicator.setOnIndexChangedListener(this);
    }


    private void initView() {
        lvCompany = (ListView) findViewById(R.id.lv_company);
        ibIndicator = (IndexBar) findViewById(R.id.ib_indicator);
        tvIndicator = (TextView) findViewById(R.id.tv_indicator);
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
                mCompanyList.add(company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onIndexChanged(String index, boolean isDown) {
        int position = -1;
        for (CompanyEntity company : mCompanyList) {
            if (TextUtils.equals(company.getName(), index)) {
                position = mCompanyList.indexOf(company);
                break;
            }
        }
        if (position != -1) {
            lvCompany.setSelection(position);
        }
        tvIndicator.setText(index);
        tvIndicator.setVisibility(isDown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setName(mCompanyList.get(position).getName());
        searchInfo.setLogo(mCompanyList.get(position).getLogo());
        searchInfo.setCode(mCompanyList.get(position).getCode());
        Intent intent = new Intent();
        intent.putExtra(Extras.SEARCH_INFO, searchInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
