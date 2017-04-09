package com.example.administrator.superandroid.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.BeautyRecycleAdapter;

import java.util.ArrayList;
import java.util.List;


public class BeautyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> imageUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        initView();
        initData();
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置适配器
        BeautyRecycleAdapter adapter=new BeautyRecycleAdapter(imageUrls,getApplicationContext());
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private void initData() {
        imageUrls = new ArrayList<>();
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144300&di=8b0ef96214ef6a958671d79b6d06fde1&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150407%2F139-15040GZ046-50.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144298&di=02cd8cf3f68365539fe4ad44cfb70e5f&imgtype=0&src=http%3A%2F%2Fwww.woyewan.com%2Fuploads%2F20120813%2Fmm%2F13213.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144294&di=269e933e805deaee0f30fce6d4f2bff9&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201606%2F20160623002.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144293&di=f0da31772240d6033b1f5b295b1e0041&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F110710%2F1209106325-6.jpg");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
