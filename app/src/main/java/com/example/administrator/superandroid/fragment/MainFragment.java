package com.example.administrator.superandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ListAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.dto.ListMainFmDto;
import com.example.administrator.superandroid.view.ParallaxListView;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment {

    private List<String> listName;
    private List<Drawable> listIcon;
    private GridView mGridView;
    private SharedPreferences sharedPreferences;

    public void initData() {
        sharedPreferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        listName = new ArrayList<>();
        listIcon = new ArrayList<>();
        listName.add(sharedPreferences.getString("username",""));
        listName.add(sharedPreferences.getString("nickName",""));
        listName.add("你好");
        listName.add("你好");
        listName.add("学校");
        listName.add("学院");
        listName.add("班级");
        listName.add("你好");
        Drawable drawable = getResources().getDrawable(R.drawable.find);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
        listIcon.add(drawable);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_main, null);
        initData();
        final ParallaxListView plv = (ParallaxListView) view.findViewById(R.id.plv);
        View mHeaderView = View.inflate(getContext(), R.layout.layout_list_header, null);
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
