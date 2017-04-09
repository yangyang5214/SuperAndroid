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
        initToolBar();
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
                        showToolBar();
                        mCurrItem = 0;
                        changeMenu();
                        break;
                    case R.id.btn_2:
                        mViewPager.setCurrentItem(1,false);
                        showToolBar();
                        mCurrItem = 1;
                        changeMenu();
                        break;
                    case R.id.btn_3:
                        mViewPager.setCurrentItem(2,false);
                        hideToolBar();
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

    /**
     * 加载数据页面顶部menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moving_top_menu, menu);
        inflater.inflate(R.menu.main_top_mine,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * onCreateOptionsMenu执行之后也会执行这个方法，即一开始menu初始化的时候也会执行一次,不同页面使用不同的menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.share);
        MenuItem item1 = menu.findItem(R.id.map);
        if (mCurrItem == 1) {
            item1.setVisible(false);
            item.setVisible(true);
        } else {
            item1.setVisible(true);
            item.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 调用这个方法后，会执行onPrepareOptionsMenu方法，onPrepareOptionsMenu方法中处理menu的一些显示问题
     */
    private void changeMenu() {
        this.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
        invalidateOptionsMenu();
    }

    /**
     * 根据需求隐藏ToolBar
     */
    private void hideToolBar() {
        if (mToolBar.getVisibility() == View.VISIBLE) {
            mToolBar.setVisibility(View.GONE);
        }
    }

    /**
     * 根据需求显示ToolBar
     */
    private void showToolBar() {
        if (mToolBar.getVisibility() == View.GONE) {
            mToolBar.setVisibility(View.VISIBLE);
        }
    }
}
