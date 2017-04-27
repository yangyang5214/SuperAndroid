package com.example.administrator.superandroid.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/10/3.
 */
public class DefineScrollView extends ScrollView {


    public DefineScrollView(Context context) {
        super(context);
    }

    public DefineScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefineScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 修复了ScrollView嵌套ListView时出现的整个页面滑到ListView底部的情况
     * @param rect
     * @return
     */
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}
