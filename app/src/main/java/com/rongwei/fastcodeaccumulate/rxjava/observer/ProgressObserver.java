package com.rongwei.fastcodeaccumulate.rxjava.observer;



import com.rongwei.fastcodeaccumulate.module.base.IListView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by maoqi on 2018/11/26.
 */
public abstract class ProgressObserver<T> implements Observer<T> {
    private boolean isRefresh = true;
    protected IListView mIBaseView;


    public ProgressObserver(IListView view) {
        super();
        this.mIBaseView = view;
    }

    public ProgressObserver(IListView view, boolean isRefresh) {
        super();
        this.mIBaseView = view;
        this.isRefresh = isRefresh;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isRefresh) {
            mIBaseView.showProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        mIBaseView.loadError();
        mIBaseView.removeProgress();
    }

    @Override
    public void onComplete() {
        mIBaseView.removeProgress();
    }
}
