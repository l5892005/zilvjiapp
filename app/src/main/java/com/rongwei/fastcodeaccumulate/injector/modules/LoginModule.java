package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.user.login.LoginActivity;

import com.rongwei.fastcodeaccumulate.module.user.login.LoginContract;
import com.rongwei.fastcodeaccumulate.module.user.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class LoginModule {
    public final LoginActivity view;

    public LoginModule(LoginActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public LoginContract.Presenter providePresenter(Repository repository) {
        return new LoginPresenter(view, repository);
    }
}
