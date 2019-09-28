package com.zzq.gankclient.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zzq.gankclient.utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {

    protected <T extends BaseViewModel> T initBaseViewModel(@NonNull Class<T> tClass) {
        BaseViewModel viewModel = ViewModelProviders.of(this).get(tClass);
        setBaseViewModel(viewModel);
        return (T)viewModel;
    }

    private void setBaseViewModel(@NonNull BaseViewModel baseViewModel) {
        BaseRepo repo = baseViewModel.getRepo();
        if (repo != null) {
            repo.getErrorLiveData().observe(this, new Observer<ErrorBean>() {
                @Override
                public void onChanged(ErrorBean errorBean) {
                    dealErrorRequest(errorBean);
                }
            });
        }
    }

    /**
     * 默认的网络请求失败处理，子类可以自定义
     */
    protected void dealErrorRequest(ErrorBean errorBean) {
        ToastUtil.showToast(errorBean.getRequest() + "请求失败，请重试");
    }
}
