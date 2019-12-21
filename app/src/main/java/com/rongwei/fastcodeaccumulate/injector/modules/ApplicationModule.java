package com.rongwei.fastcodeaccumulate.injector.modules;

import android.content.Context;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.data.source.local.LocalDataSource;
import com.rongwei.fastcodeaccumulate.data.source.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MAO on 2018/6/2.
 */

@Module
public class ApplicationModule {
    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }

    @Singleton
    @Provides
    Repository provideRepository(){
        return Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
    }
}
