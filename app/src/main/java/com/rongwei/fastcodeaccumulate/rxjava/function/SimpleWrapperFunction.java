package com.rongwei.fastcodeaccumulate.rxjava.function;

import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;
import com.rongwei.fastcodeaccumulate.utils.ToastUtil;

import io.reactivex.functions.Function;

/**
 * Created by maoqi on 2018/11/23.
 */
public class SimpleWrapperFunction<T> implements Function<BaseResultWrapper<T>, T> {
    private IBaseView mIBaseView;

    public SimpleWrapperFunction(IBaseView view) {
        this.mIBaseView = view;
    }

    public void onFailed(int failedCode, String failedInfo) {
        if (!failedInfo.isEmpty()) {
            ToastUtil.toast(failedInfo);
        }
    }

    public void tokenTimeout() {
        mIBaseView.tokenTimeout();
    }

    @Override
    public T apply(BaseResultWrapper<T> tBaseResultWrapper) throws Exception {
        String message = tBaseResultWrapper.getMessage();
        int code = tBaseResultWrapper.getCode();
        if (code == Cons.REQUEST_SUCCESS || code == Cons.REQUEST_LOCAL) {
            //成功

        } else if (tBaseResultWrapper.getCode() == Cons.REQUEST_TOKEN_TIMEOUT) {
            tokenTimeout();
        } else {
            onFailed(code, message);
        }
        return tBaseResultWrapper.getData();
    }
}
