package com.zzq.gankclient.jetpack.repository;

import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.zzq.gankclient.viewmodels.GankDataViewModel;

public class GankRepository {

    public static GankDataViewModel getGankFuliDataViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(GankDataViewModel.class);
    }

    public static GankDataViewModel getGankFuliDataViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(GankDataViewModel.class);
    }
}
