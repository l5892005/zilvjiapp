package com.rongwei.fastcodeaccumulate.module.me.money.total;

import android.content.Context;
import android.content.Intent;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.annotation.ContentView;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerTotalMoneyActivityComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.TotalMoneyActivityModule;

import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;

import javax.inject.Inject;
@ContentView( R.layout.activity_total_money_activity)
public class TotalMoneyActivityActivity extends BaseActivity implements TotalMoneyActivityContract.View {

    @Inject
    TotalMoneyActivityContract.Presenter mPresenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, TotalMoneyActivityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerTotalMoneyActivityComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .totalMoneyActivityModule(new TotalMoneyActivityModule(this))
                .build()
                .inject(this);
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
