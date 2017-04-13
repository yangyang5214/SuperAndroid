package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.ShowImageDetailsActivity;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.view.RoundImage;
import com.example.expressdelivery.view.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class MovingRecycleAdapter extends RecyclerView.Adapter<MovingRecycleAdapter.BeautyView>{
    private List<MovingDto> movingDtos;
    private Context context;
    private int selectPosition;

    public MovingRecycleAdapter(List<MovingDto> movingDtos, Context context) {
        this.movingDtos = movingDtos;
        this.context = context;
    }

    @Override
    public BeautyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.moving_recycle_item, parent, false);
        return new BeautyView(view);
    }

    @Override
    public void onBindViewHolder(BeautyView holder, int position) {
        selectPosition = position;
        Picasso.with(context)
                    .load(movingDtos.get(position).getAvatarUrl())
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_image)
                    .into(holder.roundImageView);
        holder.textUserName.setText(movingDtos.get(position).getUserName());
        holder.textTime.setText(movingDtos.get(position).getPublishTime());
        holder.textContent.setText(movingDtos.get(position).getContent());
        holder.gridView.setAdapter(new OneImageGridViewAdpter(movingDtos.get(position).getImageUrl(),context));
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionId, long id) {
                //对图片进行查看，删除
                Intent intent = new Intent(context, ShowImageDetailsActivity.class);
                intent.putExtra("ID", positionId);
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("imageList", (ArrayList<String>) movingDtos.get(selectPosition).getImageUrl());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movingDtos.size();
    }


    public static class BeautyView extends  RecyclerView.ViewHolder{
        RoundImage roundImageView;
        TextView textUserName;
        TextView textTime;
        TextView textContent;
        GridView gridView;
        public BeautyView(View itemView){
            super(itemView);
            roundImageView= (RoundImage) itemView.findViewById(R.id.image_photo);
            textUserName= (TextView) itemView.findViewById(R.id.text_name );
            textTime= (TextView) itemView.findViewById(R.id.text_time );
            gridView = (GridView) itemView.findViewById(R.id.moving_grid_view);
            textContent = (TextView) itemView.findViewById(R.id.moving_content);
        }

    }
}
