package com.example.administrator.superandroid.fragment.find;

import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.BeautyRecycleAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BeautyFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<String> imageUrls;
    private List<String> textContent;
    private BeautyRecycleAdapter adapter;

    public void initData() {
        imageUrls = new ArrayList<>();
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=439462059,3529812351&fm=23&gp=0.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144300&di=8b0ef96214ef6a958671d79b6d06fde1&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150407%2F139-15040GZ046-50.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144300&di=8b0ef96214ef6a958671d79b6d06fde1&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150407%2F139-15040GZ046-50.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144300&di=8b0ef96214ef6a958671d79b6d06fde1&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150407%2F139-15040GZ046-50.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144298&di=02cd8cf3f68365539fe4ad44cfb70e5f&imgtype=0&src=http%3A%2F%2Fwww.woyewan.com%2Fuploads%2F20120813%2Fmm%2F13213.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144297&di=4ba48ef151a6a24e565fb9de032211a2&imgtype=0&src=http%3A%2F%2Ftitanimg.titan24.com%2Fgame%2F20120904%2Fbefbdec2c56ad3725360c2905a63347a.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491415144293&di=f0da31772240d6033b1f5b295b1e0041&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F110710%2F1209106325-6.jpg");

        textContent = new ArrayList<>();
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
        textContent.add("hello");
    }



    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.activity_beauty, null);
        initView();
        initData();
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置适配器
        adapter=new BeautyRecycleAdapter(imageUrls,textContent,getContext());
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        return view;
    }

    private void initView() {
        final SwipeRefreshView swipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipeLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // 不能在onCreate中设置，这个表示当前是刷新状态，如果一进来就是刷新状态，SwipeRefreshLayout会屏蔽掉下拉事件
        //swipeRefreshLayout.setRefreshing(true);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textContent.add("hello hello");
                        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491842862574&di=a38c6a01645cada0367e9897c9d6fb2b&imgtype=0&src=http%3A%2F%2Fpic.3h3.com%2Fup%2F2017-3%2F2017325123219552650.jpg");
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "刷新了一条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);

                // System.out.println(Thread.currentThread().getName());

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);
            }
        });


        // 设置下拉加载更多
        swipeRefreshView.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 添加数据
                        for (int i = 30; i < 35; i++) {
                            textContent.add("hello hello");
                            imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491842862574&di=a38c6a01645cada0367e9897c9d6fb2b&imgtype=0&src=http%3A%2F%2Fpic.3h3.com%2Fup%2F2017-3%2F2017325123219552650.jpg");
                            // 这里要放在里面刷新，放在外面会导致刷新的进度条卡住
                            adapter.notifyDataSetChanged();
                        }

                        Toast.makeText(getContext(), "加载了" + 5 + "条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不加载状态，将加载进度收起来
                        swipeRefreshView.setLoading(false);
                    }
                }, 1200);
            }
        });
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
