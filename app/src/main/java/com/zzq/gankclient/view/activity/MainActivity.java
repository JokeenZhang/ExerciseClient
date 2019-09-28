package com.zzq.gankclient.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzq.gankclient.R;
import com.zzq.gankclient.base.BaseActivity;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.jetpack.viewmodels.GankDataViewModel;
import com.zzq.gankclient.view.adapter.GankAdapter;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

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

        mGankFuliDataViewModel = initBaseViewModel(GankDataViewModel.class);
    }

    private void loadData() {
        mGankFuliDataViewModel = ViewModelProviders.of(this).get(GankDataViewModel.class);

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
