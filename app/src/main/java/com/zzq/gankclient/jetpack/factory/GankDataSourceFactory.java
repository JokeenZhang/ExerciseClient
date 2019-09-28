package com.zzq.gankclient.jetpack.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.jetpack.datasource.GankFuliDataSource;

//public class GankDataSourceFactory<T extends PageKeyedDataSource> extends DataSource.Factory {
public class GankDataSourceFactory extends DataSource.Factory<Integer, FuliDataBean.ResultsBean> {

    private MutableLiveData<GankFuliDataSource> mFuliDataSourceMutableLiveData = new MutableLiveData<>();
    @Override
    public DataSource<Integer, FuliDataBean.ResultsBean> create() {
        GankFuliDataSource gankFuliDataSource = new GankFuliDataSource();
        mFuliDataSourceMutableLiveData.postValue(gankFuliDataSource);
        return gankFuliDataSource;
    }
}
