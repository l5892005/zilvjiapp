package com.rongwei.fastcodeaccumulate.module.fragment.main.very;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerVeryDayComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.VeryDayModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;

import javax.inject.Inject;

public class VeryDayFragment extends BaseFragment implements VeryDayContract.View {

    @Inject
    VeryDayContract.Presenter mPresenter;

    public static VeryDayFragment newInstance() {
        VeryDayFragment fragment = new VeryDayFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerVeryDayComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .veryDayModule(new VeryDayModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_very_day;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}