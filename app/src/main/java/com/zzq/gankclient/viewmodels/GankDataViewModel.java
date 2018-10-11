package com.zzq.gankclient.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.factory.GankDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GankDataViewModel extends ViewModel {

    private LiveData<PagedList<FuliDataBean.ResultsBean>> mPagedListLiveData;

    public GankDataViewModel() {
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);
        GankDataSourceFactory factory = new GankDataSourceFactory();
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)//配置是否启动PlaceHolders
                .setInitialLoadSizeHint(15)//初始化加载的数量
                .setPageSize(20)//配置分页加载的数量
                .build();

        mPagedListLiveData = (new LivePagedListBuilder(factory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<FuliDataBean.ResultsBean>> getGankFuliLiveData() {
        return mPagedListLiveData;
    }
}
