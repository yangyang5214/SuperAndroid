package com.example.administrator.superandroid.fragment.main;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.superandroid.adapter.FindFragmentAdapter;
import com.example.administrator.superandroid.fragment.find.CommunityFragment;
import com.example.administrator.superandroid.fragment.find.JobFragment;
import com.example.administrator.superandroid.fragment.find.LostFragment;
import com.example.administrator.superandroid.fragment.find.MovingFragment;
import com.example.expressdelivery.MyApplication;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.PublishMovingActivity;
import com.example.administrator.superandroid.base.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class FindFragment extends BaseFragment {


    private TabLayout tabFindFragmentTitle;                            //定义TabLayout
    private ViewPager vpFindFragmentPager;                             //定义viewPager
    private FragmentPagerAdapter findAdapter;                               //定义adapter

    private List<Fragment> listFragment;                                //定义要装fragment的列表
    private List<String> listTitle;                                     //tab名称列表

    private MovingFragment movingFragment;
    private CommunityFragment communityFragment;
    private JobFragment jobFragment;
    private LostFragment lostFragment;

    public void initData() {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_find, null);
        initData();
        initControls(view);
        return view;
    }

    /**
     * 初始化各控件
     *
     * @param view
     */
    private void initControls(View view) {

        tabFindFragmentTitle = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);
        vpFindFragmentPager = (ViewPager) view.findViewById(R.id.vp_FindFragment_pager);

        //初始化各fragment
        movingFragment = new MovingFragment();
        communityFragment = new CommunityFragment();
        jobFragment = new JobFragment();
        lostFragment = new LostFragment();

        //将fragment装进列表中
        listFragment = new ArrayList<>();
        listFragment.add(movingFragment);
        listFragment.add(communityFragment);
        listFragment.add(jobFragment);
        listFragment.add(lostFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        listTitle = new ArrayList<>();
        listTitle.add("动态");
        listTitle.add("社团");
        listTitle.add("工作");
        listTitle.add("失物");

        //设置TabLayout的模式
        tabFindFragmentTitle.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(0)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(1)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(2)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(3)));

        findAdapter = new FindFragmentAdapter(getActivity().getSupportFragmentManager(), listFragment, listTitle);

        //viewpager加载adapter
        vpFindFragmentPager.setAdapter(findAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);
        //tab_FindFragment_title.set
    }

}
