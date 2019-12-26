package com.rongwei.fastcodeaccumulate.module.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.module.base.BaseDialogFragment;
import com.rongwei.fastcodeaccumulate.utils.ImgPngUtils;

import java.util.List;

import butterknife.BindView;

public class AddCardDialogFragment extends BaseDialogFragment {


    private EditText etName;
    private RecyclerView rvList;
    private TextView tvSubmit;
    private BaseQuickAdapter<Integer, BaseViewHolder> baseQuickAdapter;

    /**
     *添加卡片或者修改卡片
     *
     * @return
     */
    public static AddCardDialogFragment newInstance() {
        AddCardDialogFragment fragment = new AddCardDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AddCardDialogFragment newInstance(String name,int current) {
        AddCardDialogFragment fragment = new AddCardDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putInt("current",current);
        fragment.setArguments(bundle);
        return fragment;
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
        return R.layout.dialog_fragment_add_card;
    }


    /**
     * 是否是修改的模式
     */
    private boolean IS_REMOD=false;

    public boolean isIS_REMOD() {
        return IS_REMOD;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        etName = mRootView.findViewById(R.id.et_name);
        rvList = mRootView.findViewById(R.id.rv_list);
        tvSubmit = mRootView.findViewById(R.id.tv_submit);
        String name = getArguments().getString("name");
        int current = getArguments().getInt("current",-1);
        if ((!TextUtils.isEmpty(name)) &&current!=-1){
            IS_REMOD=true;
            currentSelect=current;
            etName.setText(name);
        }
    }

    private int currentSelect=0;

    public int getCurrentSelect() {
        return currentSelect;
    }

    public EditText getEtName() {
        return etName;
    }

    @Override
    protected void loadData() {
        List<Integer> pngResList = ImgPngUtils.getInstance(getContext()).getPngResList();
        baseQuickAdapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.card_png_item, pngResList) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                helper.setImageResource(R.id.iv_card,item);
                View llBg = helper.getView(R.id.ll_bg);
                if (helper.getPosition()==currentSelect){
                    llBg.setBackgroundResource(R.drawable.bt_commont_select_bg);
                }else{
                    llBg.setBackgroundResource(R.drawable.bt_commont_unselect_bg);
                }
                helper.addOnClickListener(R.id.iv_card);
            }
        };
        rvList.setLayoutManager(new GridLayoutManager(getContext(),4,GridLayoutManager.HORIZONTAL,false));
        rvList.setAdapter(baseQuickAdapter);
        tvSubmit.setOnClickListener(mListener);
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentSelect=position;
                adapter.notifyDataSetChanged();
            }
        });
    }


    private View.OnClickListener mListener;

    public void setSubmitClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }
}
