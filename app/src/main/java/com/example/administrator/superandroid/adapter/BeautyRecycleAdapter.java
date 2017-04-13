package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.GalleryActivity;
import com.example.administrator.superandroid.activity.ShowImageDetailsActivity;
import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.expressdelivery.util.HttpClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class BeautyRecycleAdapter extends RecyclerView.Adapter<BeautyRecycleAdapter.BeautyView>{
    private List<BeautyDto> beautyDtoList;
    private Context context;
    public BeautyRecycleAdapter(List<BeautyDto> beautyDtoList,Context context) {
        this.beautyDtoList = beautyDtoList;
        this.context = context;
    }

    @Override
    public BeautyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_recycle_item, parent, false);
        return new BeautyView(view);
    }

    @Override
    public void onBindViewHolder(BeautyView holder, final int position) {
        Picasso.with(context)
                .load(beautyDtoList.get(position).getImageUrl())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.imageView);
        holder.textView.setText(beautyDtoList.get(position).getContent());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对图片进行查看，删除
                Intent intent = new Intent(context, ShowImageDetailsActivity.class);
                intent.putExtra("ID", 0);
                Bundle bundle=new Bundle();
                ArrayList<String> imagrList = new ArrayList<String>();
                imagrList.add(beautyDtoList.get(position).getImageUrl());
                bundle.putStringArrayList("imageList", imagrList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beautyDtoList.size();
    }


    public static class BeautyView extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public BeautyView(View itemView){
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.recycle_item_img );
            textView = (TextView) itemView.findViewById(R.id.recycle_item_text);
        }

    }
}
