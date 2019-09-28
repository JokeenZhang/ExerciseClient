package com.zzq.gankclient.jetpack.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zzq.gankclient.base.BaseRepo;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.interfaces.GankDataPictureListener;
import com.zzq.gankclient.jetpack.factory.GankDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GankRepository extends BaseRepo implements GankDataPictureListener {

    private LiveData<PagedList<FuliDataBean.ResultsBean>> mPagedListLiveData;
    private Executor mExecutor;
    private GankDataSourceFactory mFactory;
    private PagedList.Config mPagedListConfig;

    public GankRepository() {
        init();
    }

    private void init() {
        mExecutor = Executors.newFixedThreadPool(3);
        mFactory = new GankDataSourceFactory(this);
        mPagedListConfig = (new PagedList.Config.Builder())
                //配置是否启动PlaceHolders
                .setEnablePlaceholders(false)
                //初始化加载的数量
                .setInitialLoadSizeHint(20)
                //配置分页加载的数量
                .setPageSize(10)
                .build();

        mPagedListLiveData = (new LivePagedListBuilder<>(mFactory, mPagedListConfig))
                .setFetchExecutor(mExecutor)
                .build();
    }

    @Override
    public LiveData<PagedList<FuliDataBean.ResultsBean>> getGankFuliLiveData() {
        return mPagedListLiveData;
    }

    @Override
    public LiveData<PagedList<FuliDataBean.ResultsBean>> getRefreshLiveData(){
        mPagedListLiveData = (new LivePagedListBuilder<>(mFactory, mPagedListConfig))
                .setFetchExecutor(mExecutor)
                .build();
        return mPagedListLiveData;
    }
}
