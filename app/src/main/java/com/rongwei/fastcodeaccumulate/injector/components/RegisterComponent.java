package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.RegisterModule;

import com.rongwei.fastcodeaccumulate.module.user.register.RegisterActivity;

import com.rongwei.fastcodeaccumulate.module.user.register.RegisterActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity view);

}
