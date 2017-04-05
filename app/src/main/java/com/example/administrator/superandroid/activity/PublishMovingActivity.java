package com.example.administrator.superandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expressdelivery.MyApplication;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.SelectedImagesAdapter;
import com.example.administrator.superandroid.dto.PhotoUpImageItem;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishMovingActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView gridView;
    private TextView back,ok,select;
    private ArrayList<PhotoUpImageItem> arrayList;
    private SelectedImagesAdapter adapter;
    private List<String> filePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_moving);
        initdata();

    }

    public void initdata(){
        gridView = (GridView) findViewById(R.id.selected_images_gridv);
        back = (TextView) findViewById(R.id.back);
        ok = (TextView) findViewById(R.id.sure);
        select = (TextView) findViewById(R.id.select);
        if ((ArrayList<PhotoUpImageItem>) getIntent().getSerializableExtra("selectIma") == null){
            arrayList = new ArrayList<>();
        }else {
            arrayList = (ArrayList<PhotoUpImageItem>) getIntent().getSerializableExtra("selectIma");
        }
        adapter = new SelectedImagesAdapter(PublishMovingActivity.this,
                arrayList);
        gridView.setAdapter(adapter);
        back.setOnClickListener(this);
        ok.setOnClickListener(this);
        select.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void publishMoving() {
        filePaths = new ArrayList<>();
        MultipartBody.Part[] parts = null;
        if (arrayList.size()!=0){
            for (PhotoUpImageItem photoUpImageItem : arrayList) {
                filePaths.add(photoUpImageItem.getImagePath());
            }
            parts = ImageUtil.filesToMultipartBodyParts(filePaths);
        }
        Call<ResponseDto> responseBodyCall = RetrofitClient.getClient().publishMoving(parts,"1","12323313");
        responseBodyCall.enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                ResponseDto message = response.body();
                if (message.getSuccess() == true) {
                    Toast.makeText(MyApplication.getInstance(), "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getInstance(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sure:
                publishMoving();
                break;
            case R.id.select:
                Intent intent = new Intent(PublishMovingActivity.this,AlbumsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
