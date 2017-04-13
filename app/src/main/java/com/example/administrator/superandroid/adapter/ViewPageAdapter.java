package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ViewPageAdapter extends PagerAdapter {
    private ArrayList<View> listViews;
    private int size;
    private Context context;

    public ViewPageAdapter(ArrayList<View> listViews, Context context) {
        this.listViews = listViews;
        size = listViews == null ? 0 : listViews.size();
        this.context = context;
    }

    public void setListViews(ArrayList<View> listViews) {
        this.listViews = listViews;
        size = listViews == null ? 0 : listViews.size();
    }

    public int getCount() {
        return size;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
    }

    public void finishUpdate(View arg0) {
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        container.addView(listViews.get(position));
        return listViews.get(position);
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
