package com.rongwei.fastcodeaccumulate.rxjava.observer;

import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by maoqi on 2018/6/2.
 */

public abstract class SimpleWrapperObserver<T> implements Observer<BaseResultWrapper<T>> {
    protected IBaseView mIBaseView;

    public SimpleWrapperObserver(IBaseView view) {
        mIBaseView = view;
    }

    public abstract void onSuccess(T data);

    public void onFailed(String failedInfo) {
        if (mIBaseView != null) {
            mIBaseView.toastFailed(failedInfo);
        }
    }

    public void tokenTimeout(String message) {
        if (mIBaseView != null) {
            mIBaseView.tokenTimeout();
            mIBaseView.toastAlert(message);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof ConnectException
                ||throwable instanceof HttpException) {
            if (mIBaseView != null) {
                mIBaseView.toastNetError();
            }
        }
        onFinished();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart();
    }

    @Override
    public void onNext(BaseResultWrapper<T> tBaseResultWrapper) {
        String message = tBaseResultWrapper.getMessage();
        int code = tBaseResultWrapper.getCode();
        if (code == Cons.REQUEST_SUCCESS || code == Cons.REQUEST_LOCAL) {
            //成功
            if (mIBaseView != null) {
                //mIBaseView.toastSucc(message);
            }
            onSuccess(tBaseResultWrapper.getData());
        } else if (code == Cons.REQUEST_TOKEN_TIMEOUT) {
            //token失效
            tokenTimeout(message);
        } else {
            onFailed(message);
        }
    }

    @Override
    public void onComplete() {
        onFinished();
    }

    protected void onStart() {

    }

    public void fruitRewardUpperLimit() {

    }

    protected void onFinished() {

    }

}
