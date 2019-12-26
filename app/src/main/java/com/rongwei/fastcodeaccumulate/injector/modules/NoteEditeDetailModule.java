package com.rongwei.fastcodeaccumulate.injector.modules;

import com.rongwei.fastcodeaccumulate.data.source.Repository;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.module.note.edit.NoteEditeDetailActivity;

import com.rongwei.fastcodeaccumulate.module.note.edit.NoteEditeDetailContract;
import com.rongwei.fastcodeaccumulate.module.note.edit.NoteEditeDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maoqi on 2018/6/7.
 */

@Module
public class NoteEditeDetailModule {
    public final NoteEditeDetailActivity view;

    public NoteEditeDetailModule(NoteEditeDetailActivity view) {
        this.view = view;
    }


    @PerActivity
    @Provides
    public NoteEditeDetailContract.Presenter providePresenter(Repository repository) {
        return new NoteEditeDetailPresenter(view, repository);
    }
}
