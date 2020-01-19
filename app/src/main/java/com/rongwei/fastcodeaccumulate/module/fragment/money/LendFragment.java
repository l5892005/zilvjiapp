package com.rongwei.fastcodeaccumulate.module.fragment.money;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class LendFragment extends BaseFragment implements LendContract.View {

    @Inject
    LendContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private ArrayList<LeadDebotBean.DataBean> databean;

    public static LendFragment newInstance(ArrayList<LeadDebotBean.DataBean> beans) {
        LendFragment fragment = new LendFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("databean", beans);
        fragment.setArguments(bundle);
        return fragment;
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
            rvList.setAdapter(new BaseQuickAdapter<LeadDebotBean.DataBean,BaseViewHolder>(R.layout.lend_item,databean) {
                @Override
                protected void convert(BaseViewHolder helper, LeadDebotBean.DataBean item) {
                    helper.setText(R.id.tv_name,item.getMname()+"");
                    helper.setText(R.id.tv_money,item.getMoney()+"");
                    helper.setText(R.id.tv_remark,item.getMremark()+"");
                    helper.setText(R.id.tv_data,item.getCreatetime()+"");
                }
            });
        }

    }

    @Override
    protected void loadData() {

    }
}