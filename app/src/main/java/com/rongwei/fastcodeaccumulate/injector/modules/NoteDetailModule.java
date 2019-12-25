package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailActivity;

import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailContract;
import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class NoteDetailModule {
    public final NoteDetailActivity view;

    public NoteDetailModule(NoteDetailActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public NoteDetailContract.Presenter providePresenter(Repository repository) {
        return new NoteDetailPresenter(view, repository);
    }
}
