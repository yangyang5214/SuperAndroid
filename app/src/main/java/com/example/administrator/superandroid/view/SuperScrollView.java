package com.example.administrator.superandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/3/26.
 */
public class SuperScrollView extends GridView {
    public SuperScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperScrollView(Context context) {
        super(context);
    }

    public SuperScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
