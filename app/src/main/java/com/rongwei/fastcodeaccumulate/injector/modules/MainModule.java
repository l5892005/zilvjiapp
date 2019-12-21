package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.mian.MainActivity;

import com.rongwei.fastcodeaccumulate.module.mian.MainContract;
import com.rongwei.fastcodeaccumulate.module.mian.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class MainModule {
    public final MainActivity view;

    public MainModule(MainActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public MainContract.Presenter providePresenter(Repository repository) {
        return new MainPresenter(view, repository);
    }
}
