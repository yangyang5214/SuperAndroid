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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.ShowImageDetailsActivity;
import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.administrator.superandroid.dto.MarketDto;
import com.example.administrator.superandroid.view.RoundImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class MarketRecycleAdapter extends RecyclerView.Adapter<MarketRecycleAdapter.BeautyView>{
    private List<MarketDto> marketDtoList;
    private Context context;
    private int selectPosition;


    public MarketRecycleAdapter(List<MarketDto> marketDtoList, Context context) {
        this.marketDtoList = marketDtoList;
        this.context = context;
    }

    @Override
    public BeautyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.market_recycle_item, parent, false);
        return new BeautyView(view);
    }

    @Override
    public void onBindViewHolder(BeautyView holder, final int position) {
        selectPosition = position;
        Picasso.with(context)
                .load(marketDtoList.get(position).getAvatarUrl())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.roundImageView);
        holder.textUserName.setText(marketDtoList.get(position).getUserName());
        holder.textTime.setText(marketDtoList.get(position).getPublishTime());
        holder.textContent.setText(marketDtoList.get(position).getContent());
        holder.textViewPrice.setText("￥" + marketDtoList.get(position).getPrice());
        holder.gridView.setAdapter(new OneImageGridViewAdpter(marketDtoList.get(position).getImageUrl(),context));
    }

    @Override
    public int getItemCount() {
        return marketDtoList.size();
    }


    public static class BeautyView extends  RecyclerView.ViewHolder{
        RoundImage roundImageView;
        TextView textUserName;
        TextView textTime;
        TextView textContent;
        GridView gridView;
        TextView textViewPrice;
        public BeautyView(View itemView){
            super(itemView);
            roundImageView= (RoundImage) itemView.findViewById(R.id.image_photo_market);
            textUserName= (TextView) itemView.findViewById(R.id.text_name );
            textTime= (TextView) itemView.findViewById(R.id.text_time );
            textViewPrice= (TextView) itemView.findViewById(R.id.price );
            gridView = (GridView) itemView.findViewById(R.id.moving_grid_view);
            textContent = (TextView) itemView.findViewById(R.id.moving_content);
        }

    }
}
