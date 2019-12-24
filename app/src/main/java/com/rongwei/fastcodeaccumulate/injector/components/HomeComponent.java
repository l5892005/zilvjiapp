package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.injector.modules.HomeModule;

import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomeFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment view);

}
