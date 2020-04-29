package com.rongwei.fastcodeaccumulate.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;

import java.util.List;

public class ExperienceAdapter extends BaseQuickAdapter<ExperienceBean.DataBean, BaseViewHolder> {
    public ExperienceAdapter(@Nullable List<ExperienceBean.DataBean> data) {
        super(R.layout.experience_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExperienceBean.DataBean item) {
        helper.setText(R.id.tv_content,item.getContentText());
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_time,item.getCreateTime()+"");

    }
}
