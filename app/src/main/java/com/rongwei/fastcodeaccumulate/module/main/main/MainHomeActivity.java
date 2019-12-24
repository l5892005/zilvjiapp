package com.rongwei.fastcodeaccumulate.module.main.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMainHomeComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MainHomeModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomeFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends BaseActivity implements MainHomeContract.View {

    @Inject
    MainHomeContract.Presenter mPresenter;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainHomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerMainHomeComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .mainHomeModule(new MainHomeModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main_home;

    }

    @Override
    protected void initData() {

    }

    private BaseFragment[] mFragments;
    public static final int TAB_ONE = 0;
    public static final int TAB_TWO = 1;
    public static final int TAB_THREE= 2;
    @Override
    protected void initView() {
        mFragments = new BaseFragment[3];
        HomeFragment homeFragment = HomeFragment.newInstance();
        MyHabitFragment ployFragment = MyHabitFragment.newInstance();
        HomeFragment ployFragment2 = HomeFragment.newInstance();
        mFragments[0] = homeFragment;
        mFragments[1] = ployFragment;
        mFragments[2] = ployFragment2;
        bottomBar.setDefaultTabPosition(TAB_ONE);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_index:
                        selectedFragment(TAB_ONE);
                        break;
                    case R.id.tab_thow:
                        selectedFragment(TAB_TWO);
                        break;
                    case R.id.tab_three:
                        selectedFragment(TAB_THREE);
                        break;
                }
            }
        });
    }
    public int mLastPosition = -1;
    private void selectedFragment(int index) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragments[index];
        if (mLastPosition != -1) {
            ft.hide(mFragments[mLastPosition]);
        }
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.fl_content, fragment).show(fragment);
        }
        ft.commitAllowingStateLoss();
        mLastPosition = index;
    }

    @Override
    protected void loadData() {

    }

}
