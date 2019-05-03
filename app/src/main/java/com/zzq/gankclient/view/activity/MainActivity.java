package com.zzq.gankclient.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zzq.gankclient.R;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.jetpack.repository.GankRepository;
import com.zzq.gankclient.utils.LogUtils;
import com.zzq.gankclient.view.adapter.GankAdapter;
import com.zzq.gankclient.viewmodels.GankDataViewModel;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private GankDataViewModel mGankFuliDataViewModel;

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
        mAdapter = new GankAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }, 1000);

    }

    private void loadData() {
        mGankFuliDataViewModel = GankRepository.getGankFuliDataViewModel(this);
        mGankFuliDataViewModel.getGankFuliLiveData().observe(this, new Observer<PagedList<FuliDataBean.ResultsBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<FuliDataBean.ResultsBean> resultsBeans) {
                mRefreshLayout.setRefreshing(false);
                mAdapter.submitList(resultsBeans);
            }
        });
    }

    @Override
    public void onRefresh() {
        mAdapter.submitList(null);
        mGankFuliDataViewModel.getRefreshLiveData().observe(this, new Observer<PagedList<FuliDataBean.ResultsBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<FuliDataBean.ResultsBean> listBeans) {
                mRefreshLayout.setRefreshing(false);
                mAdapter.submitList(listBeans);
            }
        });
    }
}
