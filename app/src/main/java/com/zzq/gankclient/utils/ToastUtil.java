package com.zzq.gankclient.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.zzq.gankclient.MyApp;

public class ToastUtil {


    private static Toast sToast;
    private static Handler sHandler = MyApp.getInstance().getHandler();

    public static void showToast(String msg) {
        if (null == sToast) {
            sToast = Toast.makeText(MyApp.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }
        sToast.cancel();
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sToast.show();
            }
        }, 200);
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
