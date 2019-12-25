package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.tool.MyToolFragment;

import com.rongwei.fastcodeaccumulate.module.fragment.main.tool.MyToolContract;
import com.rongwei.fastcodeaccumulate.module.fragment.main.tool.MyToolPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class MyToolModule {
    public final MyToolFragment view;

    public MyToolModule(MyToolFragment view) {
        this.view = view;
    }


    @PerFragment
    @Provides
    public MyToolContract.Presenter providePresenter(Repository repository) {
        return new MyToolPresenter(view, repository);
    }
}
