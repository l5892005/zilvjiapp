package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodeActivity;

import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodeContract;
import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class AddFastCodeModule {
    public final AddFastCodeActivity view;

    public AddFastCodeModule(AddFastCodeActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public AddFastCodeContract.Presenter providePresenter(Repository repository) {
        return new AddFastCodePresenter(view, repository);
    }
}
