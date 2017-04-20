package com.example.administrator.superandroid.fragment.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.MineListViewAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserFindDataDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.view.RoundImage;
import com.example.administrator.superandroid.view.SwipeRefreshView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RoundImage roundImage;
    private TextView textView;
    private SharedPreferences sharedPreferences;
    private LinearLayout linearlayout;
    private Toolbar mToolBar;
    private TextView mTitleText;
    private ListView listView;
    private MineListViewAdapter mineListViewAdapter;
    private List<String> listName;
    private List<Integer> listIcon;

    private LinearLayout linearLayoutBeauty;
    private LinearLayout linearLayoutMoving;
    private LinearLayout linearLayoutMarket;

    private TextView textViewBeauty;
    private TextView textViewMoving;
    private TextView textViewMarket;

    public void initData() {
        getUserFindData();
    }

    private void getUserFindData() {
        Call<ResponseDto<UserFindDataDto>> responseBodyCall = RetrofitClient.getClient().getUserFindData(1);
        responseBodyCall.enqueue(new Callback<ResponseDto<UserFindDataDto>>() {
            @Override
            public void onResponse(Call<ResponseDto<UserFindDataDto>> call, Response<ResponseDto<UserFindDataDto>> response) {
                UserFindDataDto userFindData = (UserFindDataDto) response.body().getObj();
                textViewBeauty.setText(userFindData.getBeayty());
                textViewMoving.setText(userFindData.getMoving());
                textViewMarket.setText(userFindData.getMarket());
            }

            @Override
            public void onFailure(Call<ResponseDto<UserFindDataDto>> call, Throwable t) {
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        initView();
        initData();
        return view;
    }

    private void initUserData() {

    }

    private void initView() {
        linearlayout = (LinearLayout) view.findViewById(R.id.user_message);
        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        roundImage = (RoundImage) view.findViewById(R.id.image_photo);
        textView = (TextView) view.findViewById(R.id.text_nickname);
        listView = (ListView) view.findViewById(R.id.mine_list_view);

        linearLayoutBeauty = (LinearLayout) view.findViewById(R.id.beauty_line);
        linearLayoutBeauty.setOnClickListener(this);
        linearLayoutMoving = (LinearLayout) view.findViewById(R.id.moving_line);
        linearLayoutMoving.setOnClickListener(this);
        linearLayoutMarket = (LinearLayout) view.findViewById(R.id.market_line);
        linearLayoutMarket.setOnClickListener(this);

        textViewBeauty = (TextView) view.findViewById(R.id.beauty_text);
        textViewMoving = (TextView) view.findViewById(R.id.moving_text);
        textViewMarket = (TextView) view.findViewById(R.id.market_text);


        sharedPreferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        textView.setText(sharedPreferences.getString("nickName", ""));
        Picasso.with(getContext())
                .load(sharedPreferences.getString("avatarUrl", ""))
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(roundImage);
        initListData();
        listView.setAdapter(new MineListViewAdapter(getContext(), listName, listIcon));
    }

    private void initListData() {
        listName = new ArrayList<>();
        listIcon = new ArrayList<>();

        listName.add("分享");
        listIcon.add(R.drawable.share_mine);

        listName.add("意见反馈");
        listIcon.add(R.drawable.comment_mine);

        listName.add("关于");
        listIcon.add(R.drawable.comment_mine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beauty_line:
                break;
            case R.id.moving_line:
                break;
            case R.id.market_line:
                break;
        }
    }
}
