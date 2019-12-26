package com.rongwei.fastcodeaccumulate.module.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.module.base.BaseDialogFragment;
import com.rongwei.fastcodeaccumulate.utils.SizeUtils;

import butterknife.BindView;

/**
 * Created by maoqi on 2019/3/7.
 */
public class VersionUpdateDialogFragment extends BaseDialogFragment {
    //强制更新
    public static final int TYPE_FORCE = 1;
    //简单更新
    public static final int TYPE_SIMPLE = 2;
    @BindView(R.id.tv_new_version)
    TextView mTvNewVersion;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.btn_cancel)
    TextView mBtnCancel;
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;

    private int mType = 1;

    /**
     *
     * @param type 是否强制
     * @param fileUrl 地址
     * @param explain 简介
     * @param verName
     * @return
     */
    public static VersionUpdateDialogFragment newInstance(int type, String fileUrl, String explain, String verName) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("fileUrl", fileUrl);
        args.putString("explain", explain);
        args.putString("verName", verName);
        VersionUpdateDialogFragment fragment = new VersionUpdateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDialog(Dialog dialog) {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return mType == TYPE_FORCE ? R.layout.dialog_force_update : R.layout.dialog_update;
    }

    @Override
    protected void initData() {
        mType = getArguments().getInt("type");
    }

    @Override
    protected void initView() {
        setWidth(SizeUtils.dp2px(240));
        if (mBtnCancel != null) {
            mBtnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishSelf();
                }
            });
        }
        mTvNewVersion.setText("V" + getArguments().getString("verName"));
        mTvMessage.setText(getArguments().getString("explain").trim().replace("#", "\r\n"));
        mTvMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (mListener != null) {
            mBtnSubmit.setOnClickListener(mListener);
        }
    }

    private View.OnClickListener mListener;

    public void setSubmitClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData() {

    }
}
