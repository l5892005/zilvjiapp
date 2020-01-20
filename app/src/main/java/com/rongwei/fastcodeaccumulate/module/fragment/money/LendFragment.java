package com.rongwei.fastcodeaccumulate.module.fragment.money;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerLendComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.LendModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtActivity;
import com.rongwei.fastcodeaccumulate.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class LendFragment extends BaseFragment implements LendContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @Inject
    LendContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private ArrayList<LeadDebotBean.DataBean> databean;
    private BaseQuickAdapter<LeadDebotBean.DataBean, BaseViewHolder> baseQuickAdapter;

    public static LendFragment newInstance(ArrayList<LeadDebotBean.DataBean> beans) {
        LendFragment fragment = new LendFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("databean", beans);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setDatabean(ArrayList<LeadDebotBean.DataBean> databean) {
        this.databean.clear();
        this.databean.addAll(databean);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initInjector() {
        DaggerLendComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .lendModule(new LendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_lend;

    }

    @Override
    protected void initData() {
        databean = getArguments().getParcelableArrayList("databean");
    }

    @Override
    protected void initView() {
        if (databean!=null){
            rvList.setLayoutManager(new LinearLayoutManager(mActivity));
            baseQuickAdapter = new BaseQuickAdapter<LeadDebotBean.DataBean, BaseViewHolder>(R.layout.lend_item, databean) {
                @Override
                protected void convert(BaseViewHolder helper, LeadDebotBean.DataBean item) {
                    if (item.getNowstatu()==1) {
                        helper.getView(R.id.ll_item).setAlpha(0.4f);
                    }else{
                        helper.getView(R.id.ll_item).setAlpha(1f);
                    }
                    helper.setText(R.id.tv_name, item.getMname() + "");
                    helper.setText(R.id.tv_money, item.getMoney() + "");
                    helper.setText(R.id.tv_remark, item.getMremark() + "");
                    helper.setText(R.id.tv_data, DateUtils.formatTime(Long.parseLong(item.getCreatetime()),"yyyy-MM-dd HH:mm"));
                    helper.addOnClickListener(R.id.tv_money);
                }
            };
            View inflate = getLayoutInflater().inflate(R.layout.lend_item, null);
            inflate.setBackgroundColor(getActivity().getResources().getColor(R.color.color_cad));
            baseQuickAdapter.addHeaderView(inflate);
            rvList.setAdapter(baseQuickAdapter);
            baseQuickAdapter.setOnItemChildClickListener(this);
        }

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        LeadDebotBean.DataBean dataBean = databean.get(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("操作");
        builder.setMessage("请确认"+dataBean.getMname()+"的"+dataBean.getMoney()+"是否归还");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LendRebtActivity mLendRebtActivity=(LendRebtActivity) mActivity;
                mLendRebtActivity.getLeadDebotStatus(dataBean.getMid());
            }
        });
        builder.show();
    }
}