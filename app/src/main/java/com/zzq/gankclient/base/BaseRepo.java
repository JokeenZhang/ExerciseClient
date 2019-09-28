package com.zzq.gankclient.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Response;

public class BaseRepo {

    public BaseRepo() {
        errorLiveData = new MutableLiveData<>();
    }

    protected MutableLiveData<ErrorBean> errorLiveData;

    public <T> void enqueue(Call<T> call, @NonNull final MutableLiveData<T> liveData) {
        call.enqueue(new ExerciseCallback<T>(errorLiveData) {

            @Override
            public void onSuccess(Call<T> call, Response<T> response) {
                liveData.setValue(response.body());
            }
        });
    }

    public MutableLiveData<ErrorBean> getErrorLiveData() {
        return errorLiveData;
    }
}
