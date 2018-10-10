package com.zzq.gankclient.model;

import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.net.GankManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GankFuliDataSource extends PageKeyedDataSource<Integer,FuliDataBean.ResultsBean> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, FuliDataBean.ResultsBean> callback) {

        GankManager.getGankService().getFuliData(params.requestedLoadSize, 1)
                .subscribeOn(Schedulers.io())
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FuliDataBean>() {
                    @Override
                    public void accept(FuliDataBean fuliDataBean) throws Exception {
                        callback.onResult(fuliDataBean.getResults(), null, 2);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FuliDataBean.ResultsBean> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, FuliDataBean.ResultsBean> callback) {

        GankManager.getGankService().getFuliData(params.requestedLoadSize, params.key)
                .subscribeOn(Schedulers.io())
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FuliDataBean>() {
                    @Override
                    public void accept(FuliDataBean fuliDataBean) throws Exception {
                        //TODO 暂时无法判断如何才是最后一页，如果是最后一页，第二个参数为null
                        callback.onResult(fuliDataBean.getResults(), params.key + 1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }
}
