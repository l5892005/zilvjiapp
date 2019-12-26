package com.rongwei.fastcodeaccumulate.module.tool.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerCardSettingComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.CardSettingModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.dialog.AddCardDialogFragment;
import com.rongwei.fastcodeaccumulate.utils.ImgPngUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardSettingActivity extends ToolbarActivity implements CardSettingContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @Inject
    CardSettingContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_add_card)
    TextView tvAddCard;
    private BaseQuickAdapter baseQuickAdapter;
    private List<CardBean.DataBean> data=new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, CardSettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerCardSettingComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .cardSettingModule(new CardSettingModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_card_setting;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        tvAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size()>5){
                    toastFailed("暂时只开通了5个卡片打卡！以后会开通更多的");
                    return;
                }
                AddCardDialogFragment addCardDialogFragment = AddCardDialogFragment.newInstance();
                addFragment(addCardDialogFragment);
                addCardDialogFragment.setSubmitClickListener(v1 -> {
                    String name = addCardDialogFragment.getEtName().getText().toString();
                    int currentSelect = addCardDialogFragment.getCurrentSelect();
                    if (TextUtils.isEmpty(name)){
                        toastFailed("您还未取卡片的名字哟！");
                        return;
                    }
                    removeFragment(addCardDialogFragment);
                    mPresenter.AddCardType(AndroidApplication.getInstance().getUser().getUid()+"",name,ImgPngUtils.getInstance(mContext).getPngNameList().get(currentSelect),"0");
                });
            }
        });
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.card_setting);
    }

    @Override
    protected void loadData() {
        mPresenter.getCardData(AndroidApplication.getInstance().getUser().getUid() + "");
    }


    @Override
    public void getCardDataSucess(CardBean bean) {
        data.clear();
        data = bean.getData();
        baseQuickAdapter = new BaseQuickAdapter<CardBean.DataBean, BaseViewHolder>(R.layout.setting_cards_item, data) {
            @Override
            protected void convert(BaseViewHolder helper, CardBean.DataBean item) {
                helper.setImageResource(R.id.iv_img, ImgPngUtils.getInstance(mContext).getPngName(item.getImgcard()));
                helper.setText(R.id.tv_name, item.getErname());
                helper.setText(R.id.tv_count, item.getCardnum() + "天");
                helper.addOnClickListener(R.id.ll_item);
            }
        };
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CardBean.DataBean dataBean = data.get(position);
        List<String> pngNameList = ImgPngUtils.getInstance(mContext).getPngNameList();
        int postion = pngNameList.indexOf(dataBean.getImgcard());
        AddCardDialogFragment addCardDialogFragment = AddCardDialogFragment.newInstance(dataBean.getErname(),postion);
        addFragment(addCardDialogFragment);
        addCardDialogFragment.setSubmitClickListener(v1 -> {
            String name = addCardDialogFragment.getEtName().getText().toString();
            int currentSelect = addCardDialogFragment.getCurrentSelect();
            if (TextUtils.isEmpty(name)){
                toastFailed("您还未取卡片的名字哟！");
                return;
            }
            removeFragment(addCardDialogFragment);
            mPresenter.setReModeCard(AndroidApplication.getInstance().getUser().getUid()+"",dataBean.getCardid(),name,ImgPngUtils.getInstance(mContext).getPngNameList().get(currentSelect),"0");
        });

    }
}
