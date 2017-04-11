package com.example.administrator.superandroid.fragment.find;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.BeautyActivity;
import com.example.administrator.superandroid.activity.LoginActivity;
import com.example.administrator.superandroid.adapter.BeautyRecycleAdapter;
import com.example.administrator.superandroid.adapter.MovingRecycleAdapter;
import com.example.administrator.superandroid.base.BaseFragment;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.expressdelivery.MyApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovingFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<MovingDto> movingDtos;

    public void initData() {
        movingDtos = new ArrayList<>();
        movingDtos = new ArrayList<>();
        Call<ResponseDto<MovingDto>> responseBodyCall = RetrofitClient.getClient().getListMoving(20, 1);
        responseBodyCall.enqueue(new Callback<ResponseDto<MovingDto>>() {
            @Override
            public void onResponse(Call<ResponseDto<MovingDto>> call, Response<ResponseDto<MovingDto>> response) {
                ResponseDto<MovingDto> message = response.body();
                movingDtos = (List<MovingDto>) message.getObj();
            }

            @Override
            public void onFailure(Call<ResponseDto<MovingDto>> call, Throwable t) {
            }
        });
    }



    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_moving, null);
        initView();
        initData();
        //设置layoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置适配器
        MovingRecycleAdapter adapter=new MovingRecycleAdapter(movingDtos,getContext());
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        MovingFragment.SpacesItemDecoration decoration=new MovingFragment.SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_moving);
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
