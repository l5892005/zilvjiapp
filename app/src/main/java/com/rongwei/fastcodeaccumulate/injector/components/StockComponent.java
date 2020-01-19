package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.StockModule;

import com.rongwei.fastcodeaccumulate.module.me.stock.StockActivity;

import com.rongwei.fastcodeaccumulate.module.me.stock.StockActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = StockModule.class)
public interface StockComponent {
    void inject(StockActivity view);

}
