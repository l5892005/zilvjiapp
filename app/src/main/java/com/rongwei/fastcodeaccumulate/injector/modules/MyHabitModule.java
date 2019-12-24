package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitFragment;

import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitContract;
import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class MyHabitModule {
    public final MyHabitFragment view;

    public MyHabitModule(MyHabitFragment view) {
        this.view = view;
    }


    @PerFragment
    @Provides
    public MyHabitContract.Presenter providePresenter(Repository repository) {
        return new MyHabitPresenter(view, repository);
    }
}
