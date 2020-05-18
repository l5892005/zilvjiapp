package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.UserAgreementModule;

import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementActivity;

import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = UserAgreementModule.class)
public interface UserAgreementComponent {
    void inject(UserAgreementActivity view);

}
