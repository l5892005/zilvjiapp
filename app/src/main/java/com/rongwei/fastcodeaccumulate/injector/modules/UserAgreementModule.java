package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementActivity;

import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementContract;
import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class UserAgreementModule {
    public final UserAgreementActivity view;

    public UserAgreementModule(UserAgreementActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public UserAgreementContract.Presenter providePresenter(Repository repository) {
        return new UserAgreementPresenter(view, repository);
    }
}
