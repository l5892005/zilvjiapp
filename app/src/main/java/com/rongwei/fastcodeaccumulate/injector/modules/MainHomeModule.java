package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;

import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeContract;
import com.rongwei.fastcodeaccumulate.module.main.main.MainHomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class MainHomeModule {
    public final MainHomeActivity view;

    public    MainHomeModule(MainHomeActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public MainHomeContract.Presenter providePresenter(Repository repository) {
        return new MainHomePresenter(view, repository);
    }
}
