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

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public class CommunityGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> imageUrls;
    private List<String> textContent;



    public CommunityGridAdapter(Context mContext, List<String> imageUrls, List<String> textContent) {
        super();
        this.imageUrls = imageUrls;
        this.textContent = textContent;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.grid_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_item);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(imageUrls.get(position))
                .dontAnimate()
                .placeholder(R.drawable.image_defult_error)
                .into(viewHolder.imageView);
        viewHolder.textView.setText(textContent.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

}
