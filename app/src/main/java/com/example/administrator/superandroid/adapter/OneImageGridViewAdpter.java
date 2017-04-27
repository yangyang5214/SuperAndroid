package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.ShowImageDetailsActivity;
import com.example.administrator.superandroid.view.SuperScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaosan on 2017/4/13.
 */
public class OneImageGridViewAdpter extends BaseAdapter {
    private List<String> urlList;
    private Context context;
    private int positionId;

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
         positionId = position;
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
//        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                        //对图片进行查看，删除
//                        Intent intent = new Intent(context, ShowImageDetailsActivity.class);
//                        intent.putExtra("ID", positionId);
//                        Bundle bundle=new Bundle();
//                        bundle.putStringArrayList("imageList", (ArrayList<String>) urlList);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
//                    }
//        });
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}
