package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.ExperienceModule;

import com.rongwei.fastcodeaccumulate.module.me.experience.ExperienceActivity;

import com.rongwei.fastcodeaccumulate.module.me.experience.ExperienceActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ExperienceModule.class)
public interface ExperienceComponent {
    void inject(ExperienceActivity view);

}
