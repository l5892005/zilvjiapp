package com.rongwei.fastcodeaccumulate.module.tool.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.annotation.ContentView;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.event.EventTag;
import com.rongwei.fastcodeaccumulate.data.event.MessageEvent;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerCardSettingComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.CardSettingModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.dialog.AddCardDialogFragment;
import com.rongwei.fastcodeaccumulate.module.mian.MainActivity;
import com.rongwei.fastcodeaccumulate.utils.ImgPngUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
@ContentView(R.layout.activity_card_setting)
public class CardSettingActivity extends ToolbarActivity implements CardSettingContract.View, BaseQuickAdapter.OnItemChildClickListener , BaseQuickAdapter.OnItemChildLongClickListener{

    @Inject
    CardSettingContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_add_card)
    TextView tvAddCard;
    private BaseQuickAdapter baseQuickAdapter;
    private List<CardBean.DataBean> data=new ArrayList<>();
    private AlertDialog.Builder builder;

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
                TextView tvSample = helper.getView(R.id.tv_sample);
                if (item.getErname().equals(getResources().getString(R.string.add_interest_card))){
                    tvSample.setVisibility(View.VISIBLE);
                    if (data.size()>1){
                        tvSample.setText(getResources().getString(R.string.sample_sub));
                    }
                }else{
                    tvSample.setVisibility(View.GONE);
                }
                helper.setImageResource(R.id.iv_img, ImgPngUtils.getInstance(mContext).getPngName(item.getImgcard()));
                helper.setText(R.id.tv_name, item.getErname());
                helper.setText(R.id.tv_count, item.getCardnum() + "天");
                helper.addOnClickListener(R.id.ll_item);
                helper.addOnLongClickListener(R.id.ll_item);
            }
        };
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemChildClickListener(this);
        baseQuickAdapter.setOnItemChildLongClickListener(this);
        EventBus.getDefault().post(new MessageEvent(EventTag.cardSetSucess,null));
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

    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        showDeleteCardDialog(data.get(position).getUseid(),data.get(position).getCardid());
        return false;
    }
    /**
     * 两个按钮的 dialog
     */
    private void showDeleteCardDialog(String uid,int cid) {
        builder = new AlertDialog.Builder(this).setIcon(R.drawable.a1).setTitle(getResources().getString(R.string.prompt))
                .setMessage(getResources().getString(R.string.is_delete_title)).setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.deleteCardData(uid,cid);
                    }
                }).setNegativeButton(getResources().getString(R.string.cacel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }


}
