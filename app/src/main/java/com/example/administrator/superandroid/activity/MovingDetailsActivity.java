package com.example.administrator.superandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.DiscussAdapter;
import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.administrator.superandroid.dto.CommentDto;
import com.example.administrator.superandroid.dto.ListResponseDto;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.intent.RetrofitService;
import com.example.administrator.superandroid.util.HideSoftKeyboard;
import com.example.administrator.superandroid.view.DefineListView;
import com.example.administrator.superandroid.view.NoScrollGridView;
import com.example.administrator.superandroid.view.SuperImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovingDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textTime;
    private TextView textTtle;
    private TextView textContent;
    private NoScrollGridView gridView;
    private ImageView imagePhoto;
    private TextView textName;
    private MovingDto moving;
    private DefineListView discussView;

    private Toolbar mToolBar;
    private TextView mTitleText;

    private List<CommentDto> discussList;
    private Context context;
    private TextView textViewSure;
    private EditText discussContent;
    private long movingId;
    private long discusUserId;
    private long movingUserId;

    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_details);
        context = getApplicationContext();
        UpdateUI(findViewById(R.id.root_view));
        initView();
        initToolBar();
        initData();
    }

    private void initData() {
        Intent intent = this.getIntent();
        moving = (MovingDto) intent.getSerializableExtra("Moving");
        movingId = moving.getId();
        movingUserId = moving.getUserId();
        textTime.setText(moving.getPublishTime());
        textContent.setText(moving.getContent());
        textName.setText(moving.getUserName());
            Picasso.with(context)
                    .load(moving.getAvatarUrl())
                    .into(imagePhoto);
        if (moving.getImageUrl() != null) {
            gridView.setVisibility(View.VISIBLE);
            MyGridViewAdapter mGridViewAdapter = new MyGridViewAdapter(moving.getImageUrl());
            gridView.setAdapter(mGridViewAdapter);
        }
        getDiscusses(movingId);
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.line_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discusUserId = 0;
                discussContent.setFocusable(true);
                discussContent.setFocusableInTouchMode(true);
                discussContent.requestFocus();
                discussContent.findFocus();
                discussContent.setHint("说点什么吧...");
            }
        });
        discussContent = (EditText) findViewById(R.id.discuss);
        //给输入框设置监听事件
        discussContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //动态改变发送按钮的状态
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isShowSure();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textViewSure = (TextView) findViewById(R.id.sure);
        textViewSure.setOnClickListener(this);
        discussView = (DefineListView) findViewById(R.id.list);
        //listview设置点击事件
        discussView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                discusUserId = discussList.get(i).getCommentUserId();
                String userName = discussList.get(i).getCommentUserName();
                discussContent.setFocusable(true);
                discussContent.setFocusableInTouchMode(true);
                discussContent.requestFocus();
                discussContent.findFocus();
                discussContent.setHint("回复 " + userName + ":");
                InputMethodManager inputManager = (InputMethodManager) discussContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(discussContent, 0);
            }
        });
        textTime = (TextView) findViewById(R.id.text_time);
        textTtle = (TextView) findViewById(R.id.text_title);
        textContent = (TextView) findViewById(R.id.text_content);
        gridView = (NoScrollGridView) findViewById(R.id.gridView_text);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MovingDetailsActivity.this,GalleryMovingActivity.class);
//                intent.putExtra("position", position);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("images", (Serializable) moving.images);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
        imagePhoto = (ImageView) findViewById(R.id.image_photo);
        textName = (TextView) findViewById(R.id.text_name);
    }


    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
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


    public void UpdateUI(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    HideSoftKeyboard.hideSoftKeyboard(MovingDetailsActivity.this);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                UpdateUI(innerView);
            }
        }
    }

    public void getDiscusses(long movingId) {
        RetrofitService retrofitService = RetrofitClient.getClient();
        Call<List<CommentDto>> call = retrofitService.getCommentDto(movingId);
        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                if (response.isSuccessful()) {
                    discussList = new ArrayList<CommentDto>();
                    List<CommentDto> result = response.body();
                    discussList = result;
                    discussInitData();
                }
            }

            @Override
            public void onFailure(Call<List<CommentDto>> call, Throwable t) {

            }
        });
    }

    //给评论的listview填充数据
    public void discussInitData() {
        DiscussAdapter discussAdapter = new DiscussAdapter(discussList, context, movingUserId);
        discussView.setAdapter(discussAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure:
                CommentDto discuss = new CommentDto();
                discuss.setContent(discussContent.getText().toString());
                discuss.setMovingId(moving.getId());
                if (discusUserId == 0) {
                    discuss.setCommentUserId(movingUserId);

                } else {
                    if (discusUserId == movingUserId) {
                        discuss.setCommentUserId(movingUserId);
                    } else {
                        discuss.setCommentUserId(discusUserId);
                    }
                }
                discuss.setUnCommentUserId(Long.parseLong(getSharedPreferences("UserData", Context.MODE_PRIVATE).getString("userId","")));
                showdiscuss(discuss);
                discussContent.setText("");
                break;
        }
    }

    //发送按钮的状态设置
    public void isShowSure() {
        if (discussContent.getText().length() != 0) {
            textViewSure.setPressed(true);
            textViewSure.setClickable(true);
            textViewSure.setBackgroundResource(R.color.blue);
        } else {
            textViewSure.setPressed(false);
            textViewSure.setClickable(false);
            textViewSure.setBackgroundResource(R.color.colorFleet);
        }
    }


    public void showdiscuss(CommentDto commentDto) {
        Call<ResponseBody> responseBodyCall = RetrofitClient.getClient().publishdiscuss(commentDto.getMovingId(),commentDto.getContent(),commentDto.getCommentUserId(),commentDto.getUnCommentUserId());
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                getDiscusses(movingId);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }


    class MyGridViewAdapter extends BaseAdapter {

        private List<String> mList;
        private LayoutInflater mLayoutInflater;

        public MyGridViewAdapter(List<String> list) {
            this.mList = list;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public String getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            GridViewHolder viewHolder;
            if (view == null) {
                viewHolder = new GridViewHolder();
                view = mLayoutInflater.inflate(R.layout.moving_img_item_details, viewGroup, false);
                viewHolder.imageView = (SuperImageView) view.findViewById(R.id.image_details);
                view.setTag(viewHolder);
            } else {
                viewHolder = (GridViewHolder) view.getTag();
            }
            String url = getItem(i);
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
            return view;
        }
    }

    class GridViewHolder {
        SuperImageView imageView;
    }
}
