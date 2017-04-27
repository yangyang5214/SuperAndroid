package com.example.administrator.superandroid.fragment.find;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.publish.BeautyPublishActivity;
import com.example.administrator.superandroid.activity.publish.MarketPublishActivity;
import com.example.administrator.superandroid.adapter.BeautyRecycleAdapter;
import com.example.administrator.superandroid.adapter.MarketRecycleAdapter;
import com.example.administrator.superandroid.adapter.MovingRecycleAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.administrator.superandroid.dto.MarketDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarketFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<MarketDto> marketDtoList;
    private MarketRecycleAdapter adapter;
    private int offset = 1;
    private int size = 20;
    private ImageView beautyPublish;


    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_market, null);
        initView();
        refresh();
        return view;
    }

    public void initMovingData() {
        Call<List<MarketDto>> responseBodyCall = RetrofitClient.getClient().getListMarket(size, offset);
        responseBodyCall.enqueue(new Callback<List<MarketDto>>() {
            @Override
            public void onResponse(Call<List<MarketDto>> call, Response<List<MarketDto>> response) {
                List<MarketDto> result = response.body();
                if (result == null){
                    Toast.makeText(getContext(),"到底啦....",Toast.LENGTH_LONG).show();
                }else {
                    if (marketDtoList.size() == 0) {
                        marketDtoList = result;
                        setAdapter();
                    } else {
                        marketDtoList.addAll(result);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MarketDto>> call, Throwable t) {
            }
        });
    }

    private void setAdapter() {
        //设置layoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisiblePos = manager.findLastVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                        offset = offset + 1;
                        initMovingData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
        });
        //设置适配器
        adapter = new MarketRecycleAdapter(marketDtoList, getContext());
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        recyclerView.addItemDecoration(decoration);
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i] > maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }

    @Override
    public void initData() {
        marketDtoList = new ArrayList<>();
        initMovingData();
    }



    public void refresh() {
        final SwipeRefreshView swipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipeLayout);
        swipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshView.setColorSchemeResources(R.color.colorAccent, R.color.yellow, R.color.colorPrimaryDark);
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marketDtoList.clear();
                        initMovingData();
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_market);
        beautyPublish = (ImageView) view.findViewById(R.id.market_publish);
        beautyPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MarketPublishActivity.class);
                startActivity(intent);
            }
        });
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }


}
