package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.me.chardata.CharActivity;

import com.rongwei.fastcodeaccumulate.module.me.chardata.CharActivity;
import com.rongwei.fastcodeaccumulate.module.me.chardata.CharContract;
import com.rongwei.fastcodeaccumulate.module.me.chardata.CharPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class CharModule {
    public final CharActivity view;

    public CharModule(CharActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public CharContract.Presenter providePresenter(Repository repository) {
        return new CharPresenter(view, repository);
    }
}
