package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.MainModule;

import com.rongwei.fastcodeaccumulate.module.mian.MainActivity;

import com.rongwei.fastcodeaccumulate.module.mian.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity view);

}
