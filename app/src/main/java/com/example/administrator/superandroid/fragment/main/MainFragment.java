package com.example.administrator.superandroid.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.superandroid.activity.AlbumActivity;
import com.example.expressdelivery.MyApplication;
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
    private PopupWindow pop = null;

    public void initData() {
        sharedPreferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        listName = new ArrayList<>();
        listIcon = new ArrayList<>();
        listName.add("学校简介");
        listName.add("公告");
        listName.add("校历");
        listName.add("图书馆");
        listName.add("校园街景");
        listName.add("公众号");
        listName.add("一卡通");
        listName.add("水电费");
        listName.add("快递");
        listIcon.add(R.drawable.university);
        listIcon.add(R.drawable.gonggao);
        listIcon.add(R.drawable.xiaoli);
        listIcon.add(R.drawable.library);
        listIcon.add(R.drawable.map);
        listIcon.add(R.drawable.weixingzh);
        listIcon.add(R.drawable.yikatong);
        listIcon.add(R.drawable.shuidian);
        listIcon.add(R.drawable.kuaidi);
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
