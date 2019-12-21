package com.rongwei.fastcodeaccumulate.module.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.Nullable;

/**
 * Created by maoqi on 2018/6/2.
 */

public abstract class BaseFragment extends RxFragment {
    protected String TAG;
    protected BaseActivity mActivity;
    protected Unbinder mBinder;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        initData();
        mRootView = inflater.inflate(attachLayoutRes(), null);
        mBinder = ButterKnife.bind(this, mRootView);
        if (enableEventBus()) {
            EventBus.getDefault().register(this);
        }
        initInjector();
        initView();
        loadData();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (enableEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        mBinder.unbind();
    }

    @LayoutRes
    protected abstract int attachLayoutRes();

    protected abstract void initData();

    protected void initInjector() {
    }

    protected abstract void initView();

    protected abstract void loadData();

    /***
     *
     * @return 是否注册EventBus
     */
    protected boolean enableEventBus() {
        return false;
    }

    public void toastAlert(String info) {
        mActivity.toastAlert(info);
    }

    public void toastSucc(String info) {
        mActivity.toastSucc(info);
    }

    public void toastFailed(String info) {
        mActivity.toastFailed(info);
    }

    public void toastNetError() {
        mActivity.toastNetError();
    }

    public void showProgress() {
        mActivity.showProgress();
    }

    public void removeProgress() {
        mActivity.removeProgress();
    }

    public void tokenTimeout() {
        mActivity.tokenTimeout();
    }

    public <T> ObservableTransformer<T, T> initNetLifecycler() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}
