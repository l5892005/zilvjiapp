package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.me.experience.ExperienceActivity;

import com.rongwei.fastcodeaccumulate.module.me.experience.ExperienceContract;
import com.rongwei.fastcodeaccumulate.module.me.experience.ExperiencePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class ExperienceModule {
    public final ExperienceActivity view;

    public ExperienceModule(ExperienceActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public ExperienceContract.Presenter providePresenter(Repository repository) {
        return new ExperiencePresenter(view, repository);
    }
}
