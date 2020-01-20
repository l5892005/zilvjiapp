package com.rongwei.fastcodeaccumulate.module.me.money.lend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.adapter.ComFragmentAdapter;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerLendRebtComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.LendRebtModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.fragment.money.LendFragment;
import com.rongwei.fastcodeaccumulate.utils.KeyboardUtils;
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
import butterknife.OnClick;

public class LendRebtActivity extends ToolbarActivity implements LendRebtContract.View {

    @Inject
    LendRebtContract.Presenter mPresenter;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private LendFragment lendFragment;
    private LendFragment rebotFragment;

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

    @OnClick({R.id.tv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                submitAddMoneyRecord();
                break;
        }
    }

    /**
     * 提交金钱记录
     */
    private void submitAddMoneyRecord() {
        String etNameS = etName.getText().toString().trim();
        String etMoneyS = etMoney.getText().toString().trim();
        String etRemarkS = etRemark.getText().toString().trim();
        if (TextUtils.isEmpty(etNameS) && TextUtils.isEmpty(etMoneyS)) {
            toastFailed("无法保存你的数据");
            return;
        }
        LeadDebotBean.DataBean dataBean = new LeadDebotBean.DataBean();
        if (!TextUtils.isEmpty(etNameS)) {
            dataBean.setMname(etNameS);
        }
        if (!TextUtils.isEmpty(etMoneyS)) {
            dataBean.setMoney(Integer.parseInt(etMoneyS));
        }
        if (!TextUtils.isEmpty(etRemarkS)) {
            dataBean.setMremark(etRemarkS);
        }
        mPresenter.putLendRebt(AndroidApplication.getInstance().getUser().getUid(), dataBean.getMoney(), index, dataBean.getMremark(), dataBean.getMname(), 0);
        etName.setText("");
        etMoney.setText("");
        etRemark.setText("");
        KeyboardUtils.hideSoftInput(this);
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

    private int index = 0;
    private List<LeadDebotBean.DataBean> beans = new ArrayList();

    @Override
    protected void initView() {
        super.initView();

        ArrayList<LeadDebotBean.DataBean> lendBeans = new ArrayList<>();
        ArrayList<LeadDebotBean.DataBean> rebotBeans = new ArrayList<>();
        for (LeadDebotBean.DataBean bean : beans) {
            int mstate = bean.getMstate();
            if (mstate == 0) {
                lendBeans.add(bean);
            } else {
                rebotBeans.add(bean);
            }
        }
        lendFragment = LendFragment.newInstance((ArrayList<LeadDebotBean.DataBean>) lendBeans);
        rebotFragment = LendFragment.newInstance((ArrayList<LeadDebotBean.DataBean>) rebotBeans);
        fragmentList.add(lendFragment);
        fragmentList.add(rebotFragment);
        viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOffscreenPageLimit(10);
        initMagicIndicator();
        viewPager.setCurrentItem(index);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                index = position;
                if (position == 1) {
                    tvAdd.setText("欠他");
                } else {
                    tvAdd.setText("借他");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

       /* srlRefresh.setEnabled(enableRefresh());
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.color_d138));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getLendRebt(AndroidApplication.getInstance().getUser().getUid());
            }
        });*/
    }
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected String setToolbarTitle() {
        return "借还账本";
    }

    @Override
    protected void loadData() {
        mPresenter.getLendRebt(AndroidApplication.getInstance().getUser().getUid());
    }


 /*   @Override
    public void toastNetError() {
        super.toastNetError();
        if (srlRefresh.isRefreshing()) {
            srlRefresh.setRefreshing(false);
        }
    }*/


    @Override
    public void getLeadDebotSucess(List<LeadDebotBean.DataBean> beans) {
//        srlRefresh.setRefreshing(false);
        ArrayList<LeadDebotBean.DataBean> lendBeans = new ArrayList<>();
        ArrayList<LeadDebotBean.DataBean> rebotBeans = new ArrayList<>();
        for (LeadDebotBean.DataBean bean : beans) {
            int mstate = bean.getMstate();
            if (mstate == 0) {
                lendBeans.add(bean);
            } else {
                rebotBeans.add(bean);
            }
        }
        lendFragment.setDatabean(lendBeans);
        rebotFragment.setDatabean(rebotBeans);
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

    public void getLeadDebotStatus(int mid) {
        mPresenter.getLendRebtStauts(mid);
    }

}
