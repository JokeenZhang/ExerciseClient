package com.zzq.gankclient;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.repository.GankRepository;
import com.zzq.gankclient.utils.LogUtils;
import com.zzq.gankclient.view.adapter.GankAdapter;
import com.zzq.gankclient.viewmodels.GankDataViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean isStartDownload;
    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;
    private int pageIndex = 1;
    private boolean mIsFirstTimeTouchBottom = true;
    private static final int PRELOAD_SIZE = 6;
    private ArrayList<FuliDataBean.ResultsBean> mResultsBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_main);
        isStartDownload = false;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            }, 45);
        }

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mResultsBeans = new ArrayList<>();
        mAdapter = new GankAdapter(this, mResultsBeans);
        mRecyclerView.setAdapter(mAdapter);

        //RecyclerView滑动过程中不断请求layout的Request，不断调整item见的间隙，并且是在item尺寸显示前预处理，因此解决RecyclerView滑动到顶部时仍会出现移动问题
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        loadData();
    }

    private void loadData() {
        GankDataViewModel gankFuliDataViewModel = GankRepository.getGankFuliDataViewModel(this);
        gankFuliDataViewModel.getGankFuliLiveData().observe(this, new Observer<PagedList<FuliDataBean.ResultsBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<FuliDataBean.ResultsBean> resultsBeans) {
                mAdapter.submitList(resultsBeans);
                LogUtils.e("ExerciseClient_net",mAdapter.getItemCount()+"");
            }
        });

    }


}
