package com.zzq.gankclient.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zzq.gankclient.R;


/**
 * 加载对话框
 */
public class LoadingDialogFragment extends DialogFragment {

    public static String key = "loading_text";

    /**
     * 如果需要定义TextView中的text，那么就使用这个方法实例化。
     *
     * @param bundle 创建bundle，通过putString()传入的key需要是{@link #key}，否则无法解析
     * @return DialogFragment实例化对象
     */
    public static LoadingDialogFragment newInstance(@NonNull Bundle bundle) {
        LoadingDialogFragment dialogFragment = new LoadingDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loading, null);

        Bundle bundle = getArguments();
        if (bundle != null) {
            TextView textView = view.findViewById(R.id.tv_dialog_loading);
            if (!TextUtils.isEmpty(bundle.getString(key))) {
                textView.setText(bundle.getString(key));
            }
        }

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.35), ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.0f;
            window.setAttributes(windowParams);
        }
    }

    /**
     * 适用于已经实例化的对象来判断DialogFragment是否已经在显示
     * @return true：显示；false：消失
     */
    public boolean isShow() {
        return getDialog() != null && getDialog().isShowing();
    }

    @Override
    public void dismiss() {
        if (isShow()) {
            super.dismiss();
        }
    }
}
