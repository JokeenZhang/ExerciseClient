package com.zzq.gankclient.utils;

import android.content.Context;
import android.widget.Toast;

import com.zzq.gankclient.MyApp;

public class ToastUtil {


    private static Toast sToast;

    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(MyApp.getInstance(), msg, Toast.LENGTH_SHORT);
        }else {
            sToast.setText(msg);
        }
        sToast.show();
    }

    /**
     * 仅仅只是作为简化处理
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
