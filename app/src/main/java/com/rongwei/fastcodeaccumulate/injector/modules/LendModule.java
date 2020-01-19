package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.money.LendFragment;

import com.rongwei.fastcodeaccumulate.module.fragment.money.LendContract;
import com.rongwei.fastcodeaccumulate.module.fragment.money.LendPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class LendModule {
    public final LendFragment view;

    public LendModule(LendFragment view) {
        this.view = view;
    }


    @PerFragment
    @Provides
    public LendContract.Presenter providePresenter(Repository repository) {
        return new LendPresenter(view, repository);
    }
}
