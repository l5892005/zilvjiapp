package com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class MyHabitPresenter implements MyHabitContract.Presenter {
    private final MyHabitContract.View mMyHabitView;
    private final Repository mRepository;

    public MyHabitPresenter(MyHabitContract.View view, Repository mRepository) {
        this.mMyHabitView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
