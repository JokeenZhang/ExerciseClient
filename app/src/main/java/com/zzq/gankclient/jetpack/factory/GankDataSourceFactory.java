package com.zzq.gankclient.jetpack.factory;

import android.util.Log;

import androidx.paging.DataSource;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.jetpack.datasource.GankFuliDataSource;
import com.zzq.gankclient.jetpack.repository.GankRepository;

//public class GankDataSourceFactory<T extends PageKeyedDataSource> extends DataSource.Factory {
public class GankDataSourceFactory extends DataSource.Factory<Integer, FuliDataBean.ResultsBean> {

    private GankFuliDataSource mGankFuliDataSource;
    public GankDataSourceFactory(GankRepository repository) {
        mGankFuliDataSource= new GankFuliDataSource(repository);
    }

    @Override
    public DataSource<Integer, FuliDataBean.ResultsBean> create() {
        Log.e("GankDataSourceFactory", "GankDataSourceFactory create");
        return mGankFuliDataSource;
    }
}
