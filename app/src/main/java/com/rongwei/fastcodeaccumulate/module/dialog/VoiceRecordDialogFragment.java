package com.rongwei.fastcodeaccumulate.module.dialog;

import android.os.Bundle;
import android.view.WindowManager;

import com.rongwei.fastcodeaccumulate.module.base.BaseDialogFragment;
import com.rongwei.fastcodeaccumulate.utils.SizeUtils;

/**
 * Created by maoqi on 2019/3/13.
 */
public class VoiceRecordDialogFragment extends BaseDialogFragment {

    public static VoiceRecordDialogFragment newInstance() {
        Bundle args = new Bundle();
        VoiceRecordDialogFragment fragment = new VoiceRecordDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.dimAmount = 0;
        mWindow.setAttributes(lp);
    }

    @Override
    protected void initView() {
        setWidth(SizeUtils.dp2px(178));
    }

    @Override
    protected void loadData() {

    }

}
