package com.zzq.gankclient.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.jetpack.factory.GankDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GankDataViewModel extends ViewModel {

    private LiveData<PagedList<FuliDataBean.ResultsBean>> mPagedListLiveData;
    private Executor mExecutor;
    private GankDataSourceFactory mFactory;
    private PagedList.Config mPagedListConfig;

    public GankDataViewModel() {
        init();
    }

    private void init() {
        mExecutor = Executors.newFixedThreadPool(3);
        mFactory = new GankDataSourceFactory();
        mPagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)//配置是否启动PlaceHolders
                .setInitialLoadSizeHint(20)//初始化加载的数量
                .setPageSize(10)//配置分页加载的数量
                .build();

        mPagedListLiveData = (new LivePagedListBuilder<>(mFactory, mPagedListConfig))
                .setFetchExecutor(mExecutor)
                .build();
    }

    public LiveData<PagedList<FuliDataBean.ResultsBean>> getGankFuliLiveData() {
        return mPagedListLiveData;
    }

    public LiveData<PagedList<FuliDataBean.ResultsBean>> getRefreshLiveData(){
        mPagedListLiveData = (new LivePagedListBuilder<>(mFactory, mPagedListConfig))
                .setFetchExecutor(mExecutor)
                .build();
        return mPagedListLiveData;
    }
}
