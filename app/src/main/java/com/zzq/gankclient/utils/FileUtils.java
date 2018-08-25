package com.zzq.gankclient.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileUtils {

    public static void savePicture(Context context,String imageUrl, String imageTitle) {
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(context).load(imageUrl).get();
        } catch (IOException e) {

        }
        if (bitmap == null) {
//            subscriber.onError(new Exception("无法下载到图片"));
        }

        File appDir = new File(Environment.getExternalStorageDirectory(), "gankclient");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = imageTitle.replace('/', '-') + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(file);
        // 通知图库更新
        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scannerIntent);
    }
}
