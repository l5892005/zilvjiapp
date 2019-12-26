package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.user.register.RegisterActivity;

import com.rongwei.fastcodeaccumulate.module.user.register.RegisterContract;
import com.rongwei.fastcodeaccumulate.module.user.register.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class RegisterModule {
    public final RegisterActivity view;

    public RegisterModule(RegisterActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public RegisterContract.Presenter providePresenter(Repository repository) {
        return new RegisterPresenter(view, repository);
    }
}
