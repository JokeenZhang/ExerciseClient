package com.zzq.gankclient.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.zzq.gankclient.model.GankFuliDataSource;

//public class GankDataSourceFactory<T extends PageKeyedDataSource> extends DataSource.Factory {
public class GankDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<GankFuliDataSource> mFuliDataSourceMutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource create() {
        GankFuliDataSource gankFuliDataSource = new GankFuliDataSource();
        mFuliDataSourceMutableLiveData.postValue(gankFuliDataSource);
        return gankFuliDataSource;
    }
}
