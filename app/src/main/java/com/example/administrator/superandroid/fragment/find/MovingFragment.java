package com.example.administrator.superandroid.fragment.find;

import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.base.BaseFragment;


public class MovingFragment extends BaseFragment {

    public void initData() {

    }



    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_moving, null);
        initData();
        return view;
    }


}
