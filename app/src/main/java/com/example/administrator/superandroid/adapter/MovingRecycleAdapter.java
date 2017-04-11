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
import com.example.administrator.superandroid.dto.MovingDto;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class MovingRecycleAdapter extends RecyclerView.Adapter<MovingRecycleAdapter.BeautyView>{
    private List<MovingDto> movingDtos;
    private Context context;
    public MovingRecycleAdapter(List<MovingDto> movingDtos, Context context) {
        this.movingDtos = movingDtos;
        this.context = context;
    }

    @Override
    public BeautyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_recycle_item, parent, false);
        return new BeautyView(view);
    }

    @Override
    public void onBindViewHolder(BeautyView holder, int position) {
        if (movingDtos.get(position).getImageUrls().size() > 0){
            Glide.with(context)
                    .load(movingDtos.get(position).getImageUrls().get(0))
                    .dontAnimate()
                    .placeholder(R.drawable.image_defult_error)
                    .into(holder.imageView);
        }else{
            holder.imageView.setVisibility(View.GONE);
        }
        holder.textView.setText(movingDtos.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return movingDtos.size();
    }


    public static class BeautyView extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public BeautyView(View itemView){
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.recycle_item_img );
            textView= (TextView) itemView.findViewById(R.id.recycle_item_text );
        }

    }
}
