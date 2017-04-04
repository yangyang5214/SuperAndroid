package com.example.administrator.superandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.superandroid.MyApplication;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ListAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.dto.ListMainFmDto;
import com.example.administrator.superandroid.view.ParallaxListView;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment {

    private List<String> listName;
    private List<Integer> listIcon;
    private SharedPreferences sharedPreferences;

    public void initData() {
        sharedPreferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        listName = new ArrayList<>();
        listIcon = new ArrayList<>();
        listName.add("转账");
        listName.add("余额宝");
        listName.add("手机充值");
        listName.add("信用卡还款");
        listName.add("淘宝电影");
        listName.add("彩票");
        listName.add("当面付");
        listName.add("亲密付");
        listName.add("机票");
        listIcon.add(R.drawable.app_transfer);
        listIcon.add(R.drawable.app_fund);
        listIcon.add(R.drawable.app_phonecharge);
        listIcon.add(R.drawable.app_creditcard);
        listIcon.add(R.drawable.app_movie);
        listIcon.add(R.drawable.app_lottery);
        listIcon.add(R.drawable.app_facepay);
        listIcon.add(R.drawable.app_close);
        listIcon.add(R.drawable.app_plane);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_main, null);
        initData();
        final ParallaxListView plv = (ParallaxListView) view.findViewById(R.id.plv);
        View mHeaderView = View.inflate(getContext(), R.layout.layout_list_header, null);
        mHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getInstance(), "success", Toast.LENGTH_SHORT).show();
            }
        });
        final ImageView iv_header = (ImageView) mHeaderView.findViewById(R.id.iv_header);
        plv.addHeaderView(mHeaderView);
        iv_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {/*如果当前布局渲染完毕, 此方法被调用,可以获取宽高,ImageView设置进去*/
                plv.setParallaxImage(iv_header);
                iv_header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        List<ListMainFmDto> listModels = new ArrayList<>();
        ListMainFmDto list = new ListMainFmDto();
        list.setListIcon(listIcon);
        list.setListName(listName);
        listModels.add(list);
        plv.setAdapter(new ListAdapter(getContext(),listModels));
        return view;
    }
}
