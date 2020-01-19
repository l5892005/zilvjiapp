package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.TotalMoneyActivityModule;

import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityActivity;

import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = TotalMoneyActivityModule.class)
public interface TotalMoneyActivityComponent {
    void inject(TotalMoneyActivityActivity view);

}
