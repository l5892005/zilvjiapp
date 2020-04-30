package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayContract;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class VeryDayModule {
    public final VeryDayFragment view;

    public VeryDayModule(VeryDayFragment view) {
        this.view = view;
    }


    @PerFragment
    @Provides
    public VeryDayContract.Presenter providePresenter(Repository repository) {
        return new VeryDayPresenter(view, repository);
    }
}
