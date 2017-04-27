package com.example.administrator.superandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/9/20.
 */
public class DefineListView extends ListView {
    public DefineListView(Context context) {
        super(context);
    }

    public DefineListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefineListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写onMeasure 使得ListView在scrollView中，在OnMeasure阶段，即使测不出实际的高度，我们也可以给它设置AT_MOST模式以支持很大的高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST));
    }
}
