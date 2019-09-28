package com.zzq.gankclient.jetpack.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.zzq.gankclient.base.BaseViewModel;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.interfaces.GankDataPictureListener;
import com.zzq.gankclient.jetpack.repository.GankRepository;

public class GankDataViewModel extends BaseViewModel implements GankDataPictureListener {

    private final GankRepository mGankRepository;

    public GankDataViewModel() {
        mGankRepository = new GankRepository();
        setRepo(mGankRepository);
    }

    @Override
    public LiveData<PagedList<FuliDataBean.ResultsBean>> getGankFuliLiveData() {
        return mGankRepository.getGankFuliLiveData();
    }

    @Override
    public LiveData<PagedList<FuliDataBean.ResultsBean>> getRefreshLiveData(){
        return mGankRepository.getRefreshLiveData();
    }
}
