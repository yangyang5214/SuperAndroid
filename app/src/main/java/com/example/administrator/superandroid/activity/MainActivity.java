package com.example.administrator.superandroid.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ViewPagerAdapter;
import com.example.administrator.superandroid.fragment.main.FindFragment;
import com.example.administrator.superandroid.fragment.main.MainFragment;
import com.example.administrator.superandroid.fragment.main.MineFragment;
import com.example.administrator.superandroid.view.NoScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TextView mTitleText;
    private NoScrollView mViewPager;
    private RadioGroup mRadioGroup;
    private int mCurrItem = 0;

    private List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initData() {
        listFragment = new ArrayList<>();
        listFragment.add(new MainFragment());
        listFragment.add(new FindFragment());
        listFragment.add(new MineFragment());
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_1:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.btn_2:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.btn_3:
                        mViewPager.setCurrentItem(2,false);
                        mCurrItem = 2;
                        break;
                    default:
                        break;
                }
            }
        });
        mViewPager.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager(),listFragment));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRadioGroup.check(R.id.btn_1);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.btn_2);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.btn_3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager = (NoScrollView) findViewById(R.id.view_pager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

    }

    /**
     * 加载ToolBar
     */
    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleText.setText(R.string.app_name);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
    }
}
