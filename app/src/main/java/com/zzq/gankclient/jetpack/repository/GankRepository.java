package com.zzq.gankclient.jetpack.repository;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.zzq.gankclient.viewmodels.GankDataViewModel;

public class GankRepository {

    public static GankDataViewModel getGankFuliDataViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(GankDataViewModel.class);
    }

    public static GankDataViewModel getGankFuliDataViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(GankDataViewModel.class);
    }
}
