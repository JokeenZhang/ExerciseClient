package com.zzq.gankclient.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    protected BaseRepo mRepo;
    protected void setRepo(@NonNull BaseRepo repo) {
        mRepo = repo;
    }

    @Nullable
    public BaseRepo getRepo() {
        return mRepo;
    }
}
