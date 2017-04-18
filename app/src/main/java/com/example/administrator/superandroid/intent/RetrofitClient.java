package com.example.administrator.superandroid.intent;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangxiaosan on 2017/3/24.
 */

public class RetrofitClient {

//    private static final String BASE_URL = "http://10.134.240.210:8090/";
//    private static final String BASE_URL = "http://192.168.1.3:8090/";
    private static final String BASE_URL = "http://123.206.100.60:8090/";
    private static RetrofitService sRetrofitService;

    public static  RetrofitService getClient(){
        if (sRetrofitService == null){
            OkHttpClient okClient  = new OkHttpClient();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sRetrofitService = client.create(RetrofitService.class);
        }
        return sRetrofitService;
    }
}
