package com.example.administrator.superandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.superandroid.MyApplication;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.SelectedImagesAdapter;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.dto.PhotoUpImageItem;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.intent.RestClient;
import com.example.administrator.superandroid.util.ImageUtil;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        MovingDto movingdto = new MovingDto();
        movingdto.setUserId("1");
        movingdto.setContent("1234556");
        List<MultipartBody.Part> partList = new ArrayList<>();
        filePaths = new ArrayList<>();
        if (arrayList.size()!=0){
            for (PhotoUpImageItem photoUpImageItem : arrayList) {
                filePaths.add(photoUpImageItem.getImagePath());
            }
        }
        partList = ImageUtil.filesToMultipartBodyParts(filePaths);
        RestClient mRestClient = new RestClient();
        Call<ResponseDto<String>> responseBodyCall = mRestClient.getRectService().publishMoving(partList,movingdto);
        responseBodyCall.enqueue(new Callback<ResponseDto<String>>() {
            @Override
            public void onResponse(Call<ResponseDto<String>> call, Response<ResponseDto<String>> response) {
                ResponseDto<String> message = response.body();
                if (message.getSuccess() == true) {
                    Toast.makeText(MyApplication.getInstance(), "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getInstance(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<String>> call, Throwable t) {
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
            case R.id.select:
                Intent intent = new Intent(PublishMovingActivity.this,AlbumsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
