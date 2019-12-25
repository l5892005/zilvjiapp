package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.injector.modules.MyToolModule;

import com.rongwei.fastcodeaccumulate.module.fragment.main.tool.MyToolFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = MyToolModule.class)
public interface MyToolComponent {
    void inject(MyToolFragment view);

}
