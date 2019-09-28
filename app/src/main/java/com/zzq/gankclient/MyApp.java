package com.zzq.gankclient;

import android.app.Application;
import android.os.Handler;


public class MyApp extends Application {

    private static MyApp instance;
    private Handler mHandler;
    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mHandler = new Handler();
    }

    public Handler getHandler() {
        return mHandler;
    }
}
