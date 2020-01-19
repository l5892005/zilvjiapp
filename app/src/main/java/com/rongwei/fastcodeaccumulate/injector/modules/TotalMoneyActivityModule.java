package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityActivity;

import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityContract;
import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class TotalMoneyActivityModule {
    public final TotalMoneyActivityActivity view;

    public TotalMoneyActivityModule(TotalMoneyActivityActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public TotalMoneyActivityContract.Presenter providePresenter(Repository repository) {
        return new TotalMoneyActivityPresenter(view, repository);
    }
}
