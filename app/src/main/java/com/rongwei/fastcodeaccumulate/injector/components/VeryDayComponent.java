package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.injector.modules.VeryDayModule;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayFragment;


import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VeryDayModule.class)
public interface VeryDayComponent {

    void inject(VeryDayFragment view);

}
