package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.view.SuperScrollView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by wangxiaosan on 2017/4/13.
 */
public class OneImageGridViewAdpter extends BaseAdapter {
    private List<String> urlList;
    private Context context;

    public OneImageGridViewAdpter(List<String> urlList,Context context) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public Object getItem(int position) {
        return urlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder== null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }else{
            convertView.setTag(viewHolder);
        }
        Picasso.with(context)
                .load(urlList.get(position))
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}
