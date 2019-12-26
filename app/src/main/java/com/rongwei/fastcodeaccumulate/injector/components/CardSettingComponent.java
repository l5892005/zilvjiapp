package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.CardSettingModule;

import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingActivity;

import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CardSettingModule.class)
public interface CardSettingComponent {
    void inject(CardSettingActivity view);

}
