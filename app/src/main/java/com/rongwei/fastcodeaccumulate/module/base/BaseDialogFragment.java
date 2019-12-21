package com.rongwei.fastcodeaccumulate.module.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;

/**
 * Created by maoqi on 2018/10/27.
 */
public abstract class BaseDialogFragment extends RxDialogFragment implements IBaseView {
    protected BaseActivity mActivity;
    protected Unbinder mBinder;
    protected View mRootView;
    protected Repository mRepository;
    protected Window mWindow;

    @LayoutRes
    protected abstract int attachLayoutRes();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void loadData();

    protected void initDialog(Dialog dialog) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initData();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mRootView = inflater.inflate(attachLayoutRes(), null);
        mBinder = ButterKnife.bind(this, mRootView);
        mRepository = AndroidApplication.getInstance().getRepository();
        int style = initStyleRes();
        Dialog dialog;
        if (style != STYLE_NORMAL) {
            dialog = new Dialog(mActivity, initStyleRes());
        } else {
            dialog = new Dialog(mActivity);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(mRootView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        mWindow = dialog.getWindow();
        initWindow();
        initDialog(dialog);
        initView();
        loadData();
        return dialog;
    }

    /**
     * 禁止Dialog取消，包括点击外部和返回键
     * @param dialog
     */
    protected void prohibitCancel(Dialog dialog) {
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
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

    private int widthDp = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int heightDp = ViewGroup.LayoutParams.WRAP_CONTENT;

    protected void setWidth(int px) {
        widthDp = px;
    }

    protected void setHeight(int px) {
        heightDp = px;
    }

    @Override
    public void onStart() {
        super.onStart();
        mWindow.setLayout(widthDp, heightDp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    @StyleRes
    protected int initStyleRes() {
        return STYLE_NORMAL;
    }

    protected void initWindow() {
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mWindow.setGravity(Gravity.CENTER);
    }

    protected void finishSelf() {
        mActivity.removeFragment(this);
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

    public void tokenTimeout() {
        mActivity.tokenTimeout();
    }

    public void showProgress() {
        mActivity.showProgress();
    }

    public void removeProgress() {
        mActivity.removeProgress();
    }

    public <T> ObservableTransformer<T, T> initNetLifecycler() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}
