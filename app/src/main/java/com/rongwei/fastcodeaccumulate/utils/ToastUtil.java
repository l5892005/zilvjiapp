package com.rongwei.fastcodeaccumulate.utils;

import android.view.View;

import androidx.annotation.StringRes;

import com.rongwei.fastcodeaccumulate.utils.toast.ToastUtils;


/**
 * Created by 毛琦 on 2017/5/5.
 */

public class ToastUtil {

    public static void toast(String msg) {
        ToastUtils.show(msg);
    }

    public static void toast(@StringRes int resId) {
        ToastUtils.show(resId);
    }

    public static void toastBackgoround(final String msg) {
        ToastUtils.show(msg);
    }

    public static void toastBackgoround(@StringRes final int resId) {
        ToastUtils.show(resId);
    }

    public static void toastCustomView(View view) {
        ToastUtils.setView(view);
        ToastUtils.show();
    }
}
