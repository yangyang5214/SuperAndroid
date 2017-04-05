/**
 * 2015-4-2
 */
package com.example.expressdelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expressdelivery.R;
import com.example.expressdelivery.entity.SearchResult;
import com.example.expressdelivery.util.Utils;


/**
 * @author wcy
 */
public class ResultAdapter extends BaseAdapter {
    private SearchResult mSearchResult;

    public ResultAdapter(SearchResult searchResult) {
        this.mSearchResult = searchResult;
    }

    @Override
    public int getCount() {
        return mSearchResult.getData().length;
    }

    @Override
    public Object getItem(int position) {
        return mSearchResult.getData()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_holder_search_result, parent, false);
            holder = new ViewHolder();
            holder.line = convertView.findViewById(R.id.line);
            holder.ivLogistics = (ImageView) convertView.findViewById(R.id.iv_logistics);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTime.setText(mSearchResult.getData()[position].getTime());
        holder.tvDetail.setText(mSearchResult.getData()[position].getContext());
        boolean first = (position == 0);
        holder.line.setPadding(0, Utils.dp2px(context, first ? 12 : 0), 0, 0);
        holder.ivLogistics.setSelected(first);
        holder.tvTime.setSelected(first);
        holder.tvDetail.setSelected(first);
        return convertView;
    }

    private static class ViewHolder {
        private View line;
        private ImageView ivLogistics;
        private TextView tvTime;
        private TextView tvDetail;
    }
}
