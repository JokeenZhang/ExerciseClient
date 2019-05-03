package com.zzq.gankclient.jetpack.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zzq.gankclient.MyApp;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.net.GankManager;
import com.zzq.gankclient.utils.FileUtils;
import com.zzq.gankclient.utils.LogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GankFuliDataSource extends PageKeyedDataSource<Integer, FuliDataBean.ResultsBean> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, FuliDataBean.ResultsBean> callback) {

        /*GankManager.getGankService().getFuliData(params.requestedLoadSize, 1)
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
                });*/
        LogUtils.e(this, "loadInitial ");
        GankManager.getGankService().getFuliPicData(params.requestedLoadSize, 1).enqueue(new Callback<FuliDataBean>() {
            @Override
            public void onResponse(Call<FuliDataBean> call, Response<FuliDataBean> response) {
                FuliDataBean body = response.body();
                List<FuliDataBean.ResultsBean> results = body.getResults();
                for (FuliDataBean.ResultsBean bean : results) {
                    if (!TextUtils.isEmpty(bean.getUrl())) {
                        FileUtils.savePicture(MyApp.getInstance().getApplicationContext(),
                                "gank", bean.getUrl());
                    }
                }
                callback.onResult(results, null, 2);
            }

            @Override
            public void onFailure(Call<FuliDataBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FuliDataBean.ResultsBean> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, FuliDataBean.ResultsBean> callback) {
        LogUtils.e(this, "loadAfter key = " + params.key);
        //不想用RxJava了
        GankManager.getGankService().getFuliPicData(params.requestedLoadSize, params.key).enqueue(new Callback<FuliDataBean>() {
            @Override
            public void onResponse(Call<FuliDataBean> call, Response<FuliDataBean> response) {
                //TODO 暂时无法判断如何才是最后一页，如果是最后一页，第二个参数为null

                FuliDataBean body = response.body();
                List<FuliDataBean.ResultsBean> results = body.getResults();
                if (results == null || results.size() == 0) {
                    //可以这么取巧来判断已经没有更多的数据，如果没有更多数据就结束
                    callback.onResult(response.body().getResults(), null);
                    return;
                }
                for (FuliDataBean.ResultsBean bean : results) {
                    if (!TextUtils.isEmpty(bean.getUrl())) {
                        FileUtils.savePicture(MyApp.getInstance().getApplicationContext(),
                                "gank", bean.getUrl());
                    }
                }
                callback.onResult(response.body().getResults(), params.key + 1);
            }

            @Override
            public void onFailure(Call<FuliDataBean> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
