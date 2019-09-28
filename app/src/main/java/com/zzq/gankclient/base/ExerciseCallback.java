package com.zzq.gankclient.base;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ExerciseCallback<T> implements Callback<T> {

    private MutableLiveData<ErrorBean> mErrorLiveData;
    public ExerciseCallback(MutableLiveData<ErrorBean> errorLiveData) {
        if (errorLiveData == null) {
            throw new IllegalStateException("Error LiveData未初始化");
        }
        mErrorLiveData = errorLiveData;
    }

    /**
     * 判断网络请求是否成功，比如无数据
     * @return
     */
    public  boolean isSuccess(Response<T> response) {
        //默认true，即能执行onResponse就认为是成功，有数据
        return true;
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (isSuccess(response)) {
            onSuccess(call, response);
        }else {
            //自定义失败处理
            onFailure(call, new Throwable(call.request().method() + "请求失败"));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //TODO 放在基类做一个默认的处理方式
        ErrorBean errorBean = new ErrorBean(call.request());
        mErrorLiveData.setValue(errorBean);
    }
}
