package com.example.administrator.superandroid.intent;


import com.example.administrator.superandroid.dto.BeautyDto;
import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserDto;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by wangxiaosan on 2017/3/24.
 */

public interface RetrofitService {

    @GET("/user/record/login")
    Call<ResponseDto<UserDto>> login(@Query("username") String username, @Query("password") String password);

    @GET("/user/register")
    Call<ResponseDto<UserDto>> register(@Query("username") String username, @Query("password") String password);

    @GET("/user/register/code")
    Call<ResponseDto> registerForCode(@Query("code") String code, @Query("email") String email);

    @Multipart
    @POST("/moving/publish")
    Call<ResponseDto> publishMoving(
            @Part() MultipartBody.Part [] files,
            @Query("userId") String userId,
            @Query("content") String content
    );

    @GET("/find/beauty/allBeauty")
    Call<ResponseDto<BeautyDto>> getListBeauty(@Query("size") int size, @Query("offset") int offset);

    @GET("/find/moving/allMoving")
    Call<ResponseDto<MovingDto>> getListMoving(@Query("size") int size, @Query("offset") int offset);
}
