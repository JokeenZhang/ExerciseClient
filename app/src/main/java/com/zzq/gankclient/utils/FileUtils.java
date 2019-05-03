package com.zzq.gankclient.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileUtils {

    private static ExecutorService sExecutorService = Executors.newFixedThreadPool(6);
    private static void postTask() {

    }
    public static void savePicture(final Context context,final String folderName,
                                  final String imageUrl) {

        sExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(context).load(imageUrl).get();
                } catch (IOException e) {

                }
                if (bitmap == null) {
                    Log.e("gankTest","无法下载图片，URI是：" + imageUrl);
                    return;
                }

                int tempIndex = imageUrl.lastIndexOf('/');
                String fileName = imageUrl.substring(tempIndex, imageUrl.length() - 1-4);
//        String fileName = imageTitle.replace('/', '-') + ".jpg";
                File appDir = new File(context.getExternalFilesDir(folderName), fileName+".jpg");
                if (!appDir.getParentFile().exists()) {
                    appDir.getParentFile().mkdir();
                }

                if (appDir.exists()) {
                    fileName = fileName + "-1重复";
                    appDir = new File(context.getExternalFilesDir(folderName), fileName + ".jpg");
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(appDir);
                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        /*Uri uri = Uri.fromFile(appDir);
        // 通知图库更新
        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scannerIntent);*/
            }
        });


    }
}
