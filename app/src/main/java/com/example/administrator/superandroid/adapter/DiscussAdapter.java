package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.dto.CommentDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class DiscussAdapter extends BaseAdapter {
    private List<CommentDto> discussList;
    private Context context;
    private LayoutInflater mInflater;
    private long movingUserId;

    public DiscussAdapter(List<CommentDto> discussList, Context context, long movingUserId) {
        this.discussList = discussList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.movingUserId = movingUserId;
    }

    @Override
    public int getCount() {
        return discussList.size();
    }

    @Override
    public CommentDto getItem(int i) {
        return discussList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == view) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_find_discuss, null);
            viewHolder.textTime = (TextView) view.findViewById(R.id.time_discuss);
            viewHolder.textNickname = (TextView) view.findViewById(R.id.num_nicknama);
            viewHolder.textContent = (TextView) view.findViewById(R.id.discuss_content);
            viewHolder.imagePhoto = (ImageView) view.findViewById(R.id.image_photo);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textTime.setText(discussList.get(i).getCommentTime());
        viewHolder.textNickname.setText(discussList.size() - i + "楼   " + discussList.get(i).getCommentUserName());

        viewHolder.textContent.setText("回复 " + discussList.get(i).getUnCommentUserName() + ":" + discussList.get(i).getContent());
        Picasso.with(context).load(discussList.get(i).getCommentUserImage()).into(viewHolder.imagePhoto);
        return view;
    }


    class ViewHolder {
        ImageView imagePhoto;
        TextView textTime;
        TextView textNickname;
        TextView textContent;
    }
}
