package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.MainHomeModule;

import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;

import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MainHomeModule.class)
public interface MainHomeComponent {
    void inject(MainHomeActivity view);

}
