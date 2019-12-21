package com.rongwei.fastcodeaccumulate.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;

import java.util.List;

public class FastCodeAdapter extends BaseQuickAdapter<FastCodeBean.DataBean, BaseViewHolder> {
    public FastCodeAdapter(@Nullable List<FastCodeBean.DataBean> data) {
        super(R.layout.fast_code_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FastCodeBean.DataBean item) {
        helper.setText(R.id.name_r,item.getName());
        helper.setText(R.id.content_r,item.getContent());
        helper.setText(R.id.time_r,item.getCreatetime());
    }
}
