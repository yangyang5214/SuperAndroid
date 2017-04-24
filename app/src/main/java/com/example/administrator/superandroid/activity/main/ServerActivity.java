package com.example.administrator.superandroid.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.WieXinListViewAdapter;
import com.example.administrator.superandroid.dto.MarketDto;
import com.example.administrator.superandroid.dto.WeiXinDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.util.ConfigUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerActivity extends AppCompatActivity {

    private ListView listView;
    private List<WeiXinDto> list;
    private WieXinListViewAdapter wieXinListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);
        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_view_weixin);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),LibraryActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    private void initData(){
        Call<List<WeiXinDto>> responseBodyCall = RetrofitClient.getClient().getListWeixin();
        responseBodyCall.enqueue(new Callback<List<WeiXinDto>>() {
            @Override
            public void onResponse(Call<List<WeiXinDto>> call, Response<List<WeiXinDto>> response) {
                List<WeiXinDto> weiXinDtoList = response.body();
                list = weiXinDtoList;
                wieXinListViewAdapter = new WieXinListViewAdapter(getApplicationContext(),list);
                listView.setAdapter(wieXinListViewAdapter);
            }

            @Override
            public void onFailure(Call<List<WeiXinDto>> call, Throwable t) {
            }
        });
    }
}
