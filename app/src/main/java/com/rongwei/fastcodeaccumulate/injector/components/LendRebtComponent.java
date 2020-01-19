package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.LendRebtModule;

import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtActivity;

import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LendRebtModule.class)
public interface LendRebtComponent {
    void inject(LendRebtActivity view);

}
