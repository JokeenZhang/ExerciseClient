package com.zzq.gankclient;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.repository.GankRepository;
import com.zzq.gankclient.utils.LogUtils;
import com.zzq.gankclient.view.adapter.GankAdapter;
import com.zzq.gankclient.viewmodels.GankDataViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;
    private int pageIndex = 1;
    private boolean mIsFirstTimeTouchBottom = true;
    private static final int PRELOAD_SIZE = 6;
    private ArrayList<FuliDataBean.ResultsBean> mResultsBeans;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_main);
        mRefreshLayout = findViewById(R.id.srl_main_pict);
        mRefreshLayout.setOnRefreshListener(this);

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mResultsBeans = new ArrayList<>();
        mAdapter = new GankAdapter(this, mResultsBeans);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new GankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, FuliDataBean.ResultsBean bean) {

            }
        });
        mRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        GankDataViewModel gankFuliDataViewModel = GankRepository.getGankFuliDataViewModel(this);
        gankFuliDataViewModel.getGankFuliLiveData().observe(this, new Observer<PagedList<FuliDataBean.ResultsBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<FuliDataBean.ResultsBean> resultsBeans) {

                mRefreshLayout.setRefreshing(false);
                mAdapter.submitList(resultsBeans);
                LogUtils.e("ExerciseClient_net",mAdapter.getItemCount()+"");
            }
        });
    }

    //TODO 如何下拉刷新，即删除之前下载的所有数据，再填充新的数据
    @Override
    public void onRefresh() {
        //如果使用mAdapter.getCurrentList().clear();，报空指针
        if (mAdapter.getCurrentList() != null && mAdapter.getCurrentList().size() != 0) {
            LogUtils.e(MainActivity.this,"加载数据前数据量 " + mAdapter.getCurrentList().size());
            mAdapter.getCurrentList().clear();
            mAdapter.notifyDataSetChanged();
        }
       /* LogUtils.e(this,"下拉刷新前数据量 " + mAdapter.getCurrentList().size());
        mAdapter.submitList(null);
        mAdapter.notifyDataSetChanged();
        loadData();*/
        mRefreshLayout.setRefreshing(false);
    }
}
