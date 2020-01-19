package com.rongwei.fastcodeaccumulate.module.me.money.lend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.adapter.ComFragmentAdapter;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerLendRebtComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.LendRebtModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.fragment.money.LendFragment;
import com.rongwei.fastcodeaccumulate.weight.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LendRebtActivity extends ToolbarActivity implements LendRebtContract.View {

    @Inject
    LendRebtContract.Presenter mPresenter;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static void start(Context context) {
        Intent intent = new Intent(context, LendRebtActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerLendRebtComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .lendRebtModule(new LendRebtModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lend_rebt;

    }
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    @Override
    protected void initData() {
        titleList.add("借给他人");
        titleList.add("欠他人钱");

    }
    private int index=0;
    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected String setToolbarTitle() {
        return "借还账本";
    }

    @Override
    protected void loadData() {
        mPresenter.getLendRebt(AndroidApplication.getInstance().getUser().getUid());

    }

    @Override
    public void getLeadDebotSucess(List<LeadDebotBean.DataBean> beans) {
        ArrayList<LeadDebotBean.DataBean> lendBeans=new ArrayList<>();
        ArrayList<LeadDebotBean.DataBean> rebotBeans=new ArrayList<>();
        for (LeadDebotBean.DataBean bean : beans) {
            int mstate = bean.getMstate();
            if (mstate==0){
                lendBeans.add(bean);
            }else{
                rebotBeans.add(bean) ;
            }
        }
        fragmentList.add(LendFragment.newInstance((ArrayList<LeadDebotBean.DataBean>) lendBeans));
        fragmentList.add(LendFragment.newInstance((ArrayList<LeadDebotBean.DataBean>) rebotBeans));
        viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOffscreenPageLimit(10);
        initMagicIndicator();
        viewPager.setCurrentItem(index);
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleList == null ? 0 : titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(titleList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.text_333));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mContext, R.color.colorPrimary));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

}
