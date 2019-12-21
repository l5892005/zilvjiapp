package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.AddFastCodeModule;

import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodeActivity;

import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodeActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AddFastCodeModule.class)
public interface AddFastCodeComponent {
    void inject(AddFastCodeActivity view);

}
