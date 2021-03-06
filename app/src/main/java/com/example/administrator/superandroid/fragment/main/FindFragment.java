package com.example.administrator.superandroid.fragment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.superandroid.adapter.FindFragmentAdapter;
import com.example.administrator.superandroid.fragment.find.BeautyFragment;
import com.example.administrator.superandroid.fragment.find.CommunityFragment;
import com.example.administrator.superandroid.fragment.find.JobFragment;
import com.example.administrator.superandroid.fragment.find.MarketFragment;
import com.example.administrator.superandroid.fragment.find.MovingFragment;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.base.BaseFragment;

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
    private MarketFragment marketFragment;
    private BeautyFragment beautyFragment;

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
        marketFragment = new MarketFragment();
        beautyFragment = new BeautyFragment();

        //将fragment装进列表中
        listFragment = new ArrayList<>();
//        listFragment.add(communityFragment);
        listFragment.add(beautyFragment);
        listFragment.add(movingFragment);
        listFragment.add(marketFragment);
//        listFragment.add(jobFragment);


        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        listTitle = new ArrayList<>();
//        listTitle.add("社团");
        listTitle.add("校园");
        listTitle.add("动态");
        listTitle.add("二手");
//        listTitle.add("兼职");


        //设置TabLayout的模式
        tabFindFragmentTitle.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(0)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(1)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(2)));
//        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(3)));
//        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(listTitle.get(4)));

        findAdapter = new FindFragmentAdapter(getActivity().getSupportFragmentManager(), listFragment, listTitle);

        //viewpager加载adapter
        vpFindFragmentPager.setAdapter(findAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);
        //tab_FindFragment_title.set
    }

}
