package com.rongwei.fastcodeaccumulate.rxjava.transformer;


import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by maoqi on 2018/8/31.
 */
public class NetworkTransformer implements ObservableTransformer<BaseResultWrapper,BaseResultWrapper> {

    @Override
    public ObservableSource<BaseResultWrapper> apply(Observable<BaseResultWrapper> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
