package com.example.administrator.superandroid.intent;


import com.example.administrator.superandroid.dto.MovingDto;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserDto;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by wangxiaosan on 2017/3/24.
 */

public interface  RestService {

    @GET("/user/record/login")
    Call<ResponseDto<UserDto>> login(@Query("username") String username, @Query("password") String password);

    @Multipart
    @POST("/moving/publish")
    Call<ResponseDto<String>> publishMoving(@Part List<MultipartBody.Part> partList, @Part MovingDto movingDto);
}
