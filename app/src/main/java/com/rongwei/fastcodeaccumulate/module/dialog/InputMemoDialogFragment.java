package com.rongwei.fastcodeaccumulate.module.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.module.base.BaseDialogFragment;
import com.rongwei.fastcodeaccumulate.utils.SizeUtils;

public class InputMemoDialogFragment extends BaseDialogFragment{
    private TextView tvCacel;
    private TextView tvSure;
    private EditText etPage;
    private TextView tvNote;
    public final static int MEMO=1;
    public final static int NOTE=2;


    /**
     * 首页创建流书
     *
     * @return
     */
    public static InputMemoDialogFragment newInstance(String info, int type) {
        InputMemoDialogFragment fragment = new InputMemoDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("info", info);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public EditText getEtPage() {
        return etPage;
    }

    @Override
    public void onStart() {
        super.onStart();
        mWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    protected void initDialog(Dialog dialog) {
        super.initDialog(dialog);
        dialog.setCanceledOnTouchOutside(false);
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
    @Override
    protected void initWindow() {
        super.initWindow();
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_input_memo;
    }
    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        tvCacel = mRootView.findViewById(R.id.tv_cacel);
        tvSure = mRootView.findViewById(R.id.tv_sure);
        etPage = mRootView.findViewById(R.id.et_page);
        tvNote = mRootView.findViewById(R.id.tv_note);
        etPage.setText(getArguments().getString("info"));
        if (getArguments().getInt("type",0)==NOTE){
            tvNote.setText("请输入笔记名称");
            etPage.setHint("请输入在十个字以内，建议4个字左右");
            etPage.setHeight(SizeUtils.dp2px(10));
        }
        tvCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishSelf();
            }
        });
        tvSure.setOnClickListener(mListener);
    }
    @Override
    protected void loadData() {
        String page = getArguments().getString("page", "");
        etPage.setText(page);
        etPage.setSelection(page.length());
    }
    private View.OnClickListener mListener;

    public void setSubmitClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }
}
