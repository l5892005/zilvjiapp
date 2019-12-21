package com.rongwei.fastcodeaccumulate.listener;

import android.view.View;

import com.rongwei.fastcodeaccumulate.utils.AntiShakeUtils;


public abstract class AntiShakeClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        if (!AntiShakeUtils.isInvalidClick(v)) {
            click(v);
        }
    }

    public abstract void click(View v);
}