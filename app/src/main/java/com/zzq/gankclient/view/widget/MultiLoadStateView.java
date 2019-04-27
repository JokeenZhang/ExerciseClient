package com.zzq.gankclient.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class MultiLoadStateView extends FrameLayout {

    /**
     * 正在加载View
     */
    private View loadingView;
    /**
     * 内容View
     */
    private View contentView;
    /**
     * 加载失败View
     */
    private View loadFailView;

    public MultiLoadStateView(@NonNull Context context) {
        this(context, null);
    }

    public MultiLoadStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiLoadStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
