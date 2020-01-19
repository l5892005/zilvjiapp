package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.me.stock.StockActivity;

import com.rongwei.fastcodeaccumulate.module.me.stock.StockContract;
import com.rongwei.fastcodeaccumulate.module.me.stock.StockPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class StockModule {
    public final StockActivity view;

    public StockModule(StockActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public StockContract.Presenter providePresenter(Repository repository) {
        return new StockPresenter(view, repository);
    }
}
