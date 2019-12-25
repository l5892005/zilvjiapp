package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.NoteDetailModule;

import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailActivity;

import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = NoteDetailModule.class)
public interface NoteDetailComponent {
    void inject(NoteDetailActivity view);

}
