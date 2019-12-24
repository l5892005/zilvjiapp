package com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMyHabitComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MyHabitModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;

import javax.inject.Inject;

public class MyHabitFragment extends BaseFragment implements MyHabitContract.View {

    @Inject
    MyHabitContract.Presenter mPresenter;

    public static MyHabitFragment newInstance() {
        MyHabitFragment fragment = new MyHabitFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerMyHabitComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .myHabitModule(new MyHabitModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_habit;

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