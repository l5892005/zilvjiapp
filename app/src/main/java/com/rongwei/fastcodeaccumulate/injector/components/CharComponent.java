package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.CharModule;


import com.rongwei.fastcodeaccumulate.module.me.chardata.CharActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CharModule.class)
public interface CharComponent {
    void inject(CharActivity view);

}
