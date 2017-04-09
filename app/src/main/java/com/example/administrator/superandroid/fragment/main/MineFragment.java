package com.example.administrator.superandroid.fragment.main;

import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.base.BaseFragment;


public class MineFragment extends BaseFragment {

    public void initData() {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }
}
