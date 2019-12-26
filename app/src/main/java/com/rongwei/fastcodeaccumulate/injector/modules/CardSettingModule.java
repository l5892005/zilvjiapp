package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingActivity;

import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingContract;
import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class CardSettingModule {
    public final CardSettingActivity view;

    public CardSettingModule(CardSettingActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public CardSettingContract.Presenter providePresenter(Repository repository) {
        return new CardSettingPresenter(view, repository);
    }
}
