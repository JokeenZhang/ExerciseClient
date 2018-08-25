package com.zzq.gankclient.net;

import com.zzq.gankclient.config.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankRetrofit {
    private GankApiService mGankApiService;

    GankRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(new TokenIntercepter());

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2的adapter
                .baseUrl(Config.baseUrl)
                .build();

        mGankApiService = retrofit.create(GankApiService.class);
    }

    public GankApiService getGankApiService() {
        return mGankApiService;
    }
}
