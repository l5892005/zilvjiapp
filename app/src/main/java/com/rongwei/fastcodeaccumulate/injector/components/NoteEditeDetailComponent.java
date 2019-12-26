package com.rongwei.fastcodeaccumulate.injector.components;

import com.rongwei.fastcodeaccumulate.injector.PerActivity;
import com.rongwei.fastcodeaccumulate.injector.modules.NoteEditeDetailModule;

import com.rongwei.fastcodeaccumulate.module.note.edit.NoteEditeDetailActivity;

import com.rongwei.fastcodeaccumulate.module.note.edit.NoteEditeDetailActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = NoteEditeDetailModule.class)
public interface NoteEditeDetailComponent {
    void inject(NoteEditeDetailActivity view);

}
