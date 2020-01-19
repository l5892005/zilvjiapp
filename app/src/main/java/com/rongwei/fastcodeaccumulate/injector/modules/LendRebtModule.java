package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtActivity;

import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtContract;
import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class LendRebtModule {
    public final LendRebtActivity view;

    public LendRebtModule(LendRebtActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public LendRebtContract.Presenter providePresenter(Repository repository) {
        return new LendRebtPresenter(view, repository);
    }
}
