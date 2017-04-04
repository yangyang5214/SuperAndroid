package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.dto.ListMainFmDto;
import com.example.administrator.superandroid.view.SuperScrollView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> listName;
    private List<Integer> listIcon;
    private List<ListMainFmDto> listModels;

    public ListAdapter(Context mContext, List<ListMainFmDto> listModels ) {
        this.mContext = mContext;
        this.listIcon = listModels.get(0).getListIcon();
        this.listName = listModels.get(0).getListName();
        this.listModels =listModels;
    }

    @Override
    public int getCount() {
        return listModels.size();
    }

    @Override
    public Object getItem(int position) {
        return listModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_body, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (SuperScrollView) convertView.findViewById(R.id.grid_View);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GridAdapter GridAdapter = new GridAdapter(mContext, listName, listIcon);
        viewHolder.gridView.setAdapter(GridAdapter);
        return convertView;
    }

    class ViewHolder {
        SuperScrollView gridView;
    }
}
