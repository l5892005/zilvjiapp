package com.rongwei.fastcodeaccumulate.module.base;

import io.reactivex.ObservableTransformer;

/**
 * Created by maoqi on 2018/6/2.
 */

public interface IBaseView {
    void toastAlert(String info);

    void toastSucc(String info);

    void toastFailed(String info);

    void toastNetError();

    void showProgress();

    void removeProgress();

    void tokenTimeout();

    <T> ObservableTransformer<T, T> initNetLifecycler();
}
