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
import com.example.administrator.superandroid.dto.WeiXinDto;
import com.example.administrator.superandroid.view.SuperScrollView;

import java.util.List;

/**
 * Created by wangxiaosan on 2017/4/24.
 */
public class WieXinListViewAdapter extends BaseAdapter {

    private Context context;
    private List<WeiXinDto> list;
    public WieXinListViewAdapter(Context context, List<WeiXinDto> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.weixin_list_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewContent = (TextView) convertView.findViewById(R.id.text_content_weixin);
            viewHolder.textViewTime = (TextView) convertView.findViewById(R.id.text_time_weixin);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_weixin);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context)
                .load(list.get(position).getImageUrl())
                .centerCrop()
                .into(viewHolder.imageView);
        viewHolder.textViewContent.setText(list.get(position).getContent());
        viewHolder.textViewTime.setText(list.get(position).getData());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewContent;
        TextView textViewTime;
    }
}
