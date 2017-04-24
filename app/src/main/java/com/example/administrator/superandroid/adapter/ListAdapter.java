package com.example.administrator.superandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;


import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.main.LibraryActivity;
import com.example.administrator.superandroid.activity.MainActivity;
import com.example.administrator.superandroid.activity.main.ServerActivity;
import com.example.administrator.superandroid.activity.NoticeActivity;
import com.example.administrator.superandroid.activity.main.SchoolCalendarActivity;
import com.example.administrator.superandroid.activity.main.UniversityActivity;
import com.example.administrator.superandroid.dto.ListMainFmDto;
import com.example.administrator.superandroid.util.ConfigUtil;
import com.example.administrator.superandroid.view.SuperScrollView;
import com.example.expressdelivery.activity.QueryActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class ListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context mContext;
    private List<String> listName;
    private List<Integer> listIcon;
    private List<ListMainFmDto> listModels;
    private MainActivity mainActivity;

    public ListAdapter(Context mContext, List<ListMainFmDto> listModels) {
        this.mContext = mContext;
        this.listIcon = listModels.get(0).getListIcon();
        this.listName = listModels.get(0).getListName();
        this.listModels = listModels;
    }

    @Override
    public int getCount() {
        return listModels.size();
    }

    @Override
    public Object getItem(int position) {
        return listModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_body, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (SuperScrollView) convertView.findViewById(R.id.grid_View);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GridAdapter GridAdapter = new GridAdapter(mContext, listName, listIcon);
        viewHolder.gridView.setAdapter(GridAdapter);
        viewHolder.gridView.setOnItemClickListener(this);
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                Intent universityIntent = new Intent(mContext.getApplicationContext(), UniversityActivity.class);
                mContext.startActivity(universityIntent);
                break;
            case 1:
                Intent noticeIntent = new Intent(mContext.getApplicationContext(), NoticeActivity.class);
                mContext.startActivity(noticeIntent);
                break;
            case 2:
                Intent schoolCalendarIntent = new Intent(mContext.getApplicationContext(), SchoolCalendarActivity.class);
                mContext.startActivity(schoolCalendarIntent);
                break;
            case 3:
                Intent libraryIntent = new Intent(mContext.getApplicationContext(), LibraryActivity.class);
                mContext.startActivity(libraryIntent);
                break;
            case 4:
                Intent mapIntent = new Intent();
                mapIntent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(ConfigUtil.getValueByKey(mContext,"school.map.url"));
                mapIntent.setData(content_url);
                mContext.startActivity(mapIntent);
                break;
            case 5:
                Intent weixinIntent = new Intent(mContext.getApplicationContext(), ServerActivity.class);
                mContext.startActivity(weixinIntent);
                break;
            case 6:
                Toast.makeText(mContext, "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(mContext, "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Intent intent = new Intent(mContext.getApplicationContext(), QueryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

    class ViewHolder {
        SuperScrollView gridView;
    }
}
