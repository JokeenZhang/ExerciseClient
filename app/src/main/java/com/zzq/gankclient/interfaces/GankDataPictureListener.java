package com.zzq.gankclient.interfaces;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.zzq.gankclient.data.FuliDataBean;

public interface GankDataPictureListener {

    LiveData<PagedList<FuliDataBean.ResultsBean>> getGankFuliLiveData();

    LiveData<PagedList<FuliDataBean.ResultsBean>> getRefreshLiveData();
}
