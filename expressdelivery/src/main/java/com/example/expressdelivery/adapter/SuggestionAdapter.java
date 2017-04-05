package com.example.expressdelivery.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.expressdelivery.R;
import com.example.expressdelivery.entity.CompanyEntity;

import java.util.List;


/**
 * Created by wcy on 2016/6/28.
 */
public class SuggestionAdapter extends BaseAdapter {
    private List<CompanyEntity> mSuggestionList;

    public SuggestionAdapter(List<CompanyEntity> companyList) {
        mSuggestionList = companyList;
    }

    @Override
    public int getCount() {
        return mSuggestionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSuggestionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_suggestion, parent, false);
            holder = new ViewHolder();
            holder.tvSuggestion = (TextView) convertView.findViewById(R.id.tv_suggestion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvSuggestion.setText(Html.fromHtml(mSuggestionList.get(position).getName()));
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvSuggestion;
    }
}
