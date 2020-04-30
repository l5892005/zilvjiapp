package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.VideoDetailModule;

import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailActivity;

import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = VideoDetailModule.class)
public interface VideoDetailComponent {
    void inject(VideoDetailActivity view);

}
