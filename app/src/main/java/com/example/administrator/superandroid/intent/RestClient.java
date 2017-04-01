package com.example.administrator.superandroid.intent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangxiaosan on 2017/3/24.
 */

public class RestClient {

    private Retrofit mRetrofit;
    private static final String BASE_URL = "http://123.206.100.60:8090/";
    private RestService mService;

    //构造方法
    public RestClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(RestService.class);
    }

    public RestService getRectService() {
        if (mService != null) {
            return mService;
        }
        return null;
    }
}
