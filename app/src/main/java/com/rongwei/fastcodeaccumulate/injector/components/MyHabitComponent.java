package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.injector.modules.MyHabitModule;

import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = MyHabitModule.class)
public interface MyHabitComponent {
    void inject(MyHabitFragment view);

}
