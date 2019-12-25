package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.LoginModule;

import com.rongwei.fastcodeaccumulate.module.user.login.LoginActivity;

import com.rongwei.fastcodeaccumulate.module.user.login.LoginActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity view);

}
