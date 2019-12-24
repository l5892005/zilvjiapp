package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomeFragment;

import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomeContract;
import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class HomeModule {
    public final HomeFragment view;

    public HomeModule(HomeFragment view) {
        this.view = view;
    }


    @PerFragment
    @Provides
    public HomeContract.Presenter providePresenter(Repository repository) {
        return new HomePresenter(view, repository);
    }
}
