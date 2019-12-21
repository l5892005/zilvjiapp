package com.rongwei.fastcodeaccumulate.rxjava.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by maoqi on 2018/6/2.
 */

public abstract class SimpleObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
