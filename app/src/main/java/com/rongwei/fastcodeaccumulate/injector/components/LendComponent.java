package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.injector.modules.LendModule;

import com.rongwei.fastcodeaccumulate.module.fragment.money.LendFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = LendModule.class)
public interface LendComponent {
    void inject(LendFragment view);

}
