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
import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.expressdelivery.util.HttpClient;

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
    public void onBindViewHolder(BeautyView holder, int position) {
        Glide.with(context)
                .load(beautyDtoList.get(position).getImageUrl())
                .dontAnimate()
                .placeholder(R.drawable.image_defult_error)
                .into(holder.imageView);
        holder.textView.setText(beautyDtoList.get(position).getContent());
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
