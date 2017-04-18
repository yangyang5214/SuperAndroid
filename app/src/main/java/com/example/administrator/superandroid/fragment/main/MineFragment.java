package com.example.administrator.superandroid.fragment.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.ListViewAdapter;
import com.example.administrator.superandroid.adapter.MineListViewAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.view.ImageViewPlus;
import com.example.administrator.superandroid.view.RoundImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends BaseFragment {

    private RoundImage roundImage;
    private TextView textView;
    private SharedPreferences sharedPreferences;
    private LinearLayout linearlayout;
    private Toolbar mToolBar;
    private TextView mTitleText;
    private ListView listView;
    private MineListViewAdapter mineListViewAdapter;
    private List<String> listName;
    private List<Integer> listIcon;

    public void initData() {
        sharedPreferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        textView.setText(sharedPreferences.getString("nickName",""));
        Picasso.with(getContext())
                .load(sharedPreferences.getString("avatarUrl",""))
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(roundImage);
        initListData();
        listView.setAdapter(new MineListViewAdapter(getContext(),listName,listIcon));
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        initView();
        return view;
    }

    private void initView() {
        linearlayout = (LinearLayout) view.findViewById(R.id.user_message);
        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        roundImage = (RoundImage) view.findViewById(R.id.image_photo);
        textView = (TextView) view.findViewById(R.id.text_nickname);
        listView = (ListView) view.findViewById(R.id.mine_list_view);
    }
    private void initListData(){
        listName = new ArrayList<>();
        listIcon = new ArrayList<>();

        listName.add("我的赞");
        listIcon.add(R.drawable.good_mine);

        listName.add("");
        listIcon.add(0);

        listName.add("分享");
        listIcon.add(R.drawable.share_mine);

        listName.add("意见反馈");
        listIcon.add(R.drawable.comment_mine);

        listName.add("关于");
        listIcon.add(R.drawable.comment_mine);
    }

}
