package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailActivity;

import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailContract;
import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class VideoDetailModule {
    public final VideoDetailActivity view;

    public VideoDetailModule(VideoDetailActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public VideoDetailContract.Presenter providePresenter(Repository repository) {
        return new VideoDetailPresenter(view, repository);
    }
}
