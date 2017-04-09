package com.example.administrator.superandroid.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * viewpager  禁止左右滑动
 * Created by zl on 2017/4/9.
 */
public class NoScrollView extends ViewPager{
    public NoScrollView(Context context) {
        super(context);
    }

    public NoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
