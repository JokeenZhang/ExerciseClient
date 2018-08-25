package com.zzq.gankclient.utils;

import android.util.Log;

import com.zzq.gankclient.BuildConfig;

public class LogUtils {

    private static String TAG = "GankClient";
    private static boolean isDebug = BuildConfig.LOG_DEBUG;

    public static void e(Object obj, String msg) {
        if (isDebug) {
            Log.e(obj.getClass().getSimpleName(), msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    private static <T> void e(String tag, T t) {
        if (isDebug) {
            Log.e(tag, t + "");
        }
    }
}
