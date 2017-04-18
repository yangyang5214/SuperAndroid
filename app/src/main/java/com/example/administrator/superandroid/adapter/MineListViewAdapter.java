package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;

import java.util.List;

/**
 * Created by zhanglong on 2017/4/18.
 */
public class MineListViewAdapter extends BaseAdapter{

    private Context context;
    private List<String> listName;
    private List<Integer> listIcon;


    public MineListViewAdapter(Context mContext,List<String> listName,List<Integer> listIcon) {
        super();
        this.listName = listName;
        this.listIcon = listIcon;
        this.context = mContext;
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
                    R.layout.mine_list_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_item);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setBackgroundResource(listIcon.get(position));
        viewHolder.textView.setText(listName.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
