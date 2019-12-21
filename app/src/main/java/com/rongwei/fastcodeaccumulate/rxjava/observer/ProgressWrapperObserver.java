package com.rongwei.fastcodeaccumulate.rxjava.observer;


import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

/**
 * Created by maoqi on 2018/11/26.
 */
public abstract class ProgressWrapperObserver<T> extends SimpleWrapperObserver<T> {
    private boolean isRefresh = true;

    public ProgressWrapperObserver(IBaseView view) {
        super(view);
    }

    public ProgressWrapperObserver(IBaseView view, boolean isRefresh) {
        super(view);
        this.isRefresh = isRefresh;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRefresh && mIBaseView != null) {
            mIBaseView.showProgress();
        }
    }

    @Override
    protected void onFinished() {
        super.onFinished();
        if (mIBaseView != null) {
            mIBaseView.removeProgress();
        }
    }
}
