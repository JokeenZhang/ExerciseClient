package com.zzq.gankclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.zzq.gankclient.R;
import com.zzq.gankclient.config.ViewConfig;

import uk.co.senab.photoview.PhotoView;

public class ImageActivity extends AppCompatActivity {

    private PhotoView mShowImagePv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mShowImagePv = findViewById(R.id.pv_show_image);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(ViewConfig.showImageAction);
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(mShowImagePv);
        }
    }
}
