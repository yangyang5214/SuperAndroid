package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.expressdelivery.util.HttpClient;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class BeautyRecycleAdapter extends RecyclerView.Adapter<BeautyRecycleAdapter.BeautyView>{
    private List<String> imageUrls;
    private Context context;
    public BeautyRecycleAdapter(List<String> imageUrls,Context context) {
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @Override
    public BeautyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_recycle_item, parent, false);
        return new BeautyView(view);
    }

    @Override
    public void onBindViewHolder(BeautyView holder, int position) {
        Glide.with(context)
                .load(imageUrls.get(position))
                .dontAnimate()
                .placeholder(R.drawable.image_defult)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


    public static class BeautyView extends  RecyclerView.ViewHolder{
        ImageView imageView;
        public BeautyView(View itemView){
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.recycle_item_img );
        }

    }
}
