package com.example.administrator.superandroid.intent;


import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.administrator.superandroid.dto.CommentDto;
import com.example.administrator.superandroid.dto.ListResponseDto;
import com.example.administrator.superandroid.dto.MarketDto;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserDto;
import com.example.administrator.superandroid.dto.UserFindDataDto;
import com.example.administrator.superandroid.dto.WeiXinDto;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by wangxiaosan on 2017/3/24.
 */

public interface RetrofitService {

    @GET("/user/login")
    Call<ResponseDto<UserDto>> login(@Query("username") String username, @Query("password") String password);

    @GET("/user/register")
    Call<ResponseDto<UserDto>> register(@Query("username") String username, @Query("password") String password);

    @GET("/user/register/code")
    Call<ResponseDto> registerForCode(@Query("code") String code, @Query("email") String email);

    @Multipart
    @POST("find/moving/publish")
    Call<ResponseDto> publishMoving(
            @Part() List<MultipartBody.Part> files,
            @Query("userId") String userId,
            @Query("content") String content,
            @Query("price") String price,
            @Query("type") int type
    );

    @GET("/find/moving/allMoving")
    Call<ListResponseDto<MovingDto>> getListMoving(@Query("size") int size, @Query("offset") int offset);

    @GET("/find/beauty/allBeauty")
    Call<List<BeautyDto>> getListBeauty(@Query("size") int size, @Query("offset") int offset);

    @GET("/find/market/allMarket")
    Call<List<MarketDto>> getListMarket(@Query("size") int size, @Query("offset") int offset);

    @GET("/find/allWeiXin")
    Call<List<WeiXinDto>> getListWeixin();

    @GET("/user/find/data")
    Call<ResponseDto<UserFindDataDto>> getUserFindData(@Query("userId") long userId);

    @GET("/moving/comment")
    Call<List<CommentDto>> getCommentDto(@Query("movingId") long movingId);

    @POST("/moving/publishdiscuss")
    Call<String> publishdiscuss(CommentDto commentDto);
}
